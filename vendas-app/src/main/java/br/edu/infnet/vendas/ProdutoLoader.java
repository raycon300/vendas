package br.edu.infnet.vendas;

import br.edu.infnet.vendas.model.domain.Literatura;
import br.edu.infnet.vendas.model.domain.Produto;
import br.edu.infnet.vendas.model.domain.Vendedor;
import br.edu.infnet.vendas.model.domain.Vestuario;
import br.edu.infnet.vendas.model.service.ProdutoService;
import br.edu.infnet.vendas.model.service.VendedorService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

import static java.lang.Boolean.parseBoolean;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

@Component
@Order(2)
public class ProdutoLoader implements ApplicationRunner {

    private static final String FILES_NAME = "files/produtos.txt";
    private static final String SEPARADOR = ";";
    private static final int TIPO = 0;
    private static final int DESCRICAO = 1;
    private static final int CODIGO = 2;
    private static final int PRECO = 3;
    private static final int ESTOQUE = 4;
    private static final int AUTOR = 5;
    private static final int NUMERO_DE_PAGINAS = 6;
    private static final int TAMANHO = 5;
    private static final int COR = 6;
    private static final int ID_VENDEDOR = 7;
    private static final String LIVRO = "L";
    private static final String ROUPA = "R";
    private final ProdutoService produtoService;
    private final VendedorService vendedorService;


    public ProdutoLoader(ProdutoService produtoService,
                         VendedorService vendedorService) {
        this.produtoService = produtoService;
        this.vendedorService = vendedorService;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {

        final var vendedores = new LinkedHashMap<Integer, Vendedor>();
        try (var bufferedReader = new BufferedReader(new FileReader(FILES_NAME))) {
            String linha;

            while ((linha = bufferedReader.readLine()) != null) {
                String[] params = linha.split(SEPARADOR);
                Produto produto = criarProduto(params);
                var email = Integer.valueOf(params[ID_VENDEDOR]);
                var vendedor = vendedores.computeIfAbsent(email, vendedorService::findById);
                produto.setVendedor(vendedor);
                incluir(produto);
            }
        }
        imprimirItensCarregados();
        imprimirProdutosPorVendedor(vendedorService.obterLista());
    }

    private Produto criarProduto(String[] params) {
        var tipo = params[TIPO];

        return switch (tipo) {
            case LIVRO -> criarLivro(params);
            case ROUPA -> criarRoupa(params);
            default -> throw new IllegalStateException("Unexpected value: [" + tipo + "]");
        };
    }


    private Vestuario criarRoupa(String[] params) {
        Vestuario vestuario = new Vestuario();
        popularInformacoesBase(params, vestuario);
        vestuario.setTamanho(params[TAMANHO]);
        vestuario.setCor(params[COR]);
        return vestuario;
    }


    private Literatura criarLivro(String[] params) {
        Literatura literatura = new Literatura();
        popularInformacoesBase(params, literatura);
        literatura.setAutor(params[AUTOR]);
        literatura.setNumeroDePaginas(parseInt(params[NUMERO_DE_PAGINAS]));
        return literatura;
    }


    private void incluir(Produto produto) {
        try {
            produtoService.incluir(produto);
        } catch (Exception e) {
            System.out.println("Erro ao incluir produto: " + e.getMessage());
        }
    }

    private void popularInformacoesBase(String[] params, Produto produto) {
        produto.setDescricao(params[DESCRICAO]);
        produto.setCodigo(parseInt(params[CODIGO]));
        produto.setPreco(parseFloat(params[PRECO]));
        produto.setEstoque(parseBoolean(params[ESTOQUE]));
    }

    private void imprimirItensCarregados() {
        System.out.println("------------------Produtos carregados------------------");
        produtoService.obterLista().forEach(System.out::println);
        System.out.println("\n");
    }

    private void imprimirProdutosPorVendedor(List<Vendedor> vendedors) {
        System.out.println("------------------Produtos por vendedor------------------");
        vendedors.forEach(vendedor -> {
            System.out.println("Produtos do vendedor: " + vendedor.getNome());
            var result = produtoService.obterLista(vendedor.getId());
            if(Objects.isNull(result) || result.isEmpty()) {
                System.out.println("Nenhum produto encontrado");

            } else {
                    result.forEach(System.out::println);
            }
            System.out.println("\n");
        });
    }

}
