package br.edu.infnet.vendas;

import br.edu.infnet.vendas.model.domain.Endereco;
import br.edu.infnet.vendas.model.domain.Vendedor;
import br.edu.infnet.vendas.model.service.VendedorService;
import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

import static java.lang.String.format;

@Component
@Order(1)
public class VendedorLoader implements ApplicationRunner {

    private static final String FILE_NAME = "files/vendedores.txt";
    private static final String SEPARADOR = ";";
    private static final int NOME = 0;
    private static final int CPF = 1;
    private static final int EMAIL = 2;
    private static final int NUM_CEP = 3;
    public static final String CEP_API_URL = "https://viacep.com.br/ws/%s/json/";
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
        vendedor.setEndereco( carregarEndereco(params[NUM_CEP]) );
        return vendedor;
    }
    
    private Endereco carregarEndereco(String numeroCep){
        
        String enderecoURL = format(CEP_API_URL, numeroCep);
        var request = createRequest(enderecoURL);
        var optionalResponseBody = executeRequest(request);
        return optionalResponseBody.map(this::parseResponse).orElse(null);
    }


    private Request createRequest(String enderecoURL) {

        return new Request.Builder()
                .url(enderecoURL)
                .method("GET", null)
                .build();

    }


    private Optional<ResponseBody> executeRequest(Request request) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        try(Response response = client.newCall(request).execute()) {
            if (response.isSuccessful())
                return Optional.ofNullable(response.body());
        } catch (Exception e) {
            System.out.println("Erro ao executar requisição: " + e.getMessage());
        }
        return Optional.empty();
    }


    private Endereco parseResponse(ResponseBody responseBody){
        try {
            return new Gson().fromJson(responseBody.string(), Endereco.class);
        } catch (IOException e) {
            System.out.println("Erro ao converter resposta: " + e.getMessage());
        }
        return null;
    }


    private void incluir(Vendedor vendedor) {
        try {
            if(Objects.nonNull(vendedor.getEndereco())) {
                vendedorService.incluir(vendedor);
            }
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
