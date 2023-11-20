package br.edu.infnet.vendas;

import br.edu.infnet.vendas.model.domain.Endereco;
import br.edu.infnet.vendas.model.domain.Vendedor;
import br.edu.infnet.vendas.model.service.VendedorService;
import okhttp3.Request;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;

@Component
@Order(1)
public class VendedorLoader implements ApplicationRunner {

    private static final String FILE_NAME = "files/vendedores.txt";
    private static final String SEPARADOR = ";";
    private static final int NOME = 0;
    private static final int CPF = 1;
    private static final int EMAIL = 2;
    private static final int NUM_CEP = 3;
    private final VendedorService vendedorService;


    public VendedorLoader(VendedorService vendedorService) {
        this.vendedorService = vendedorService;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {

        try (var bufferedReader = new BufferedReader(new FileReader(FILE_NAME))) {
            String linha;

            while ((linha = bufferedReader.readLine()) != null) {
                String[] params = linha.split(SEPARADOR);
                Vendedor vendedor = criarVendedor(params);
                incluir(vendedor);
            }
        }
        imprimirItensCarregados();
    }


    private Vendedor criarVendedor(String[] params) {
        Vendedor vendedor = new Vendedor();
        vendedor.setNome(params[NOME]);
        vendedor.setCpf(params[CPF]);
        vendedor.setEmail(params[EMAIL]);
        vendedor.setEndereco(new Endereco(params[NUM_CEP]));
        return vendedor;
    }

    private Request createRequest(String enderecoURL) {

        return new Request.Builder()
                .url(enderecoURL)
                .method("GET", null)
                .build();

    }

    private void incluir(Vendedor vendedor) {
        try {
            vendedorService.incluir(vendedor);
        } catch (Exception e) {
            System.out.println("Erro ao incluir vendedor: " + e.getMessage());
        }
    }

    private void imprimirItensCarregados() {
        System.out.println("------------------Vendedores carregados------------------");
        vendedorService.obterLista().forEach(System.out::println);
        System.out.println("\n");
    }
}
