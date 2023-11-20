package br.edu.infnet.vendas.controller;

import br.edu.infnet.vendas.model.service.*;
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
    @Autowired
    private InformacaoService informacaoService;


    @GetMapping(value = "/")
    public String showHome(Model model) {
        model.addAttribute("informacoes", informacaoService.obterLista());

        model.addAttribute("qtdVendedor", vendedorService.obterQuantidade());
        model.addAttribute("qtdProduto", produtoService.obterQuantidade());
        model.addAttribute("qtdLiteratura", literaturaService.obterQuantidade());
        model.addAttribute("qtdVestuario", vestuarioService.obterQuantidade());

        return "home";
    }
}
