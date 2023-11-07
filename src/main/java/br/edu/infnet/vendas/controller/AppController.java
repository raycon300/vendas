package br.edu.infnet.vendas.controller;

import br.edu.infnet.vendas.model.service.LiteraturaService;
import br.edu.infnet.vendas.model.service.ProdutoService;
import br.edu.infnet.vendas.model.service.VendedorService;
import br.edu.infnet.vendas.model.service.VestuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @Autowired
    private VendedorService vendedorService;
    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private LiteraturaService literaturaService;
    @Autowired
    private VestuarioService vestuarioService;


    @GetMapping(value = "/")
    public String showHome(Model model) {
        model.addAttribute("qtdVendedor", vendedorService.obterQuantidade());
        model.addAttribute("qtdProduto", produtoService.obterQuantidade());
        model.addAttribute("qtdLiteratura", literaturaService.obterQuantidade());
        model.addAttribute("qtdVestuario", vestuarioService.obterQuantidade());
        return "home";
    }

    @GetMapping(value = "/vendedor/lista")
    public String obterListaVendedor(Model model) {
        model.addAttribute("titulo", "Lista de Vendedores");
        model.addAttribute("tipo", "vendedor");
        model.addAttribute("listagem", vendedorService.obterLista());
        return showHome(model);
    }

    @GetMapping(value = "/produto/lista")
    public String obterListaProduto(Model model) {
        model.addAttribute("titulo", "Lista de Produtos");
        model.addAttribute("tipo", "produto");
        model.addAttribute("listagem", produtoService.obterLista());
        return showHome(model);
    }

    @GetMapping(value = "/literatura/lista")
    public String obterListaLiteratura(Model model) {
        model.addAttribute("titulo", "Lista de Literaturas");
        model.addAttribute("tipo", "literatura");
        model.addAttribute("listagem", literaturaService.obterLista());
        return showHome(model);
    }

    @GetMapping(value = "/vestuario/lista")
    public String obterListaVestuario(Model model) {
        model.addAttribute("titulo", "Lista de Vestuários");
        model.addAttribute("tipo", "vestuario");
        model.addAttribute("listagem", vestuarioService.obterLista());
        return showHome(model);
    }

}
