package br.edu.infnet.vendas;

import br.edu.infnet.vendas.model.domain.Literatura;
import br.edu.infnet.vendas.model.service.LiteraturaService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;

import static java.lang.Boolean.parseBoolean;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

@Component
public class LiteraturaLoader implements ApplicationRunner {

    private static final String FILE_NAME = "files/livros.txt";
    private static final String SEPARADOR = ";";
    private static final int DESCRICAO = 0;
    private static final int CODIGO = 1;
    private static final int PRECO = 2;
    private static final int ESTOQUE = 3;
    private static final int AUTOR = 4;
    private static final int NUMERO_DE_PAGINAS = 5;
    private final LiteraturaService literaturaService;


    public LiteraturaLoader(LiteraturaService literaturaService) {
        this.literaturaService = literaturaService;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {

        try (var bufferedReader = new BufferedReader(new FileReader(FILE_NAME))) {
            String linha;

            while ((linha = bufferedReader.readLine()) != null) {
                String[] params = linha.split(SEPARADOR);
                Literatura literatura = criarLivro(params);
                literaturaService.incluir(literatura);
            }
        }
        imprimirItensCarregados();
    }


    private Literatura criarLivro(String[] params) {
        Literatura literatura = new Literatura();
        literatura.setDescricao(params[DESCRICAO]);
        literatura.setCodigo(parseInt(params[CODIGO]));
        literatura.setPreco(parseFloat(params[PRECO]));
        literatura.setEstoque(parseBoolean(params[ESTOQUE]));
        literatura.setAutor(params[AUTOR]);
        literatura.setNumeroDePaginas(parseInt(params[NUMERO_DE_PAGINAS]));
        return literatura;
    }


    private void imprimirItensCarregados() {
        System.out.println("------------------Livros carregadas------------------");
        literaturaService.obterLista().forEach(System.out::println);
        System.out.println("\n");
    }
}
