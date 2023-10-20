package br.edu.infnet.vendas;

import br.edu.infnet.vendas.model.domain.Vestuario;
import br.edu.infnet.vendas.model.service.VestuarioService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;

import static java.lang.Boolean.parseBoolean;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

@Component
public class VestuarioLoader implements ApplicationRunner {

    private static final String FILE_NAME = "files/roupas.txt";
    private static final String SEPARADOR = ";";
    private static final int DESCRICAO = 0;
    private static final int CODIGO = 1;
    private static final int PRECO = 2;
    private static final int ESTOQUE = 3;
    private static final int TAMANHO = 4;
    private static final int COR = 5;
    private final VestuarioService vestuarioService;


    public VestuarioLoader(VestuarioService vestuarioService) {
        this.vestuarioService = vestuarioService;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {

        try (var bufferedReader = new BufferedReader(new FileReader(FILE_NAME))) {
            String linha;

            while ((linha = bufferedReader.readLine()) != null) {
                String[] params = linha.split(SEPARADOR);
                Vestuario vestuario = criarRoupa(params);
                vestuarioService.incluir(vestuario);
            }
        }
        imprimirItensCarregados();
    }


    private Vestuario criarRoupa(String[] params) {
        Vestuario vestuario = new Vestuario();
        vestuario.setDescricao(params[DESCRICAO]);
        vestuario.setCodigo(parseInt(params[CODIGO]));
        vestuario.setPreco(parseFloat(params[PRECO]));
        vestuario.setEstoque(parseBoolean(params[ESTOQUE]));
        vestuario.setTamanho(params[TAMANHO]);
        vestuario.setCor(params[COR]);
        return vestuario;
    }


    private void imprimirItensCarregados() {
        System.out.println("------------------Roupas carregadas------------------");
        vestuarioService.obterLista().forEach(System.out::println);
        System.out.println("\n");
    }
}
