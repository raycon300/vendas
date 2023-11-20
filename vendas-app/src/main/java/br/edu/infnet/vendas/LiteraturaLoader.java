package br.edu.infnet.vendas;

import br.edu.infnet.vendas.model.domain.Literatura;
import br.edu.infnet.vendas.model.domain.Produto;
import br.edu.infnet.vendas.model.domain.Vendedor;
import br.edu.infnet.vendas.model.service.LiteraturaService;
import br.edu.infnet.vendas.model.service.ProdutoService;
import br.edu.infnet.vendas.model.service.VendedorService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedHashMap;

import static java.lang.Boolean.parseBoolean;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

@Component
@Order(4)
public class LiteraturaLoader implements ApplicationRunner {

    private static final String FILE_NAME = "files/livros.txt";
    private static final String SEPARADOR = ";";
    private static final int DESCRICAO = 0;
    private static final int CODIGO = 1;
    private static final int PRECO = 2;
    private static final int ESTOQUE = 3;
    private static final int AUTOR = 4;
    private static final int NUMERO_DE_PAGINAS = 5;
    private static final int ID_VENDEDOR = 6;
    private final LiteraturaService literaturaService;
    private final ProdutoService produtoService;
    private final VendedorService vendedorService;


    public LiteraturaLoader(LiteraturaService literaturaService,
                            ProdutoService produtoService,
                            VendedorService vendedorService) {
        this.literaturaService = literaturaService;
        this.produtoService = produtoService;
        this.vendedorService = vendedorService;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        final var vendedores = new LinkedHashMap<Integer, Vendedor>();
        try (var bufferedReader = new BufferedReader(new FileReader(FILE_NAME))) {
            String linha;

            while ((linha = bufferedReader.readLine()) != null) {
                String[] params = linha.split(SEPARADOR);
                var produto = criarLivro(params);
                var idVendedor = Integer.valueOf(params[ID_VENDEDOR]);
                if( produtoService.isProdutoNaoCadastrado(produto.getDescricao())) {
                    var vendedor = vendedores.computeIfAbsent(idVendedor, vendedorService::findById);
                    produto.setVendedor(vendedor);
                    incluir(produto);
                }
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


    private void incluir(Produto produto) {
        try {
            produtoService.incluir(produto);
        } catch (Exception e) {
            System.out.println("Erro ao incluir literatura: " + e.getMessage());
        }
    }

    private void imprimirItensCarregados() {
        System.out.println("------------------Livros carregadas------------------");
        literaturaService.obterLista().forEach(System.out::println);
        System.out.println("\n");
    }
}
