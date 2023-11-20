package br.edu.infnet.vendas.controller;

import br.edu.infnet.vendas.model.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static java.util.Objects.nonNull;

@Controller
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private AppController appController;

    @PostMapping(value = "/produto/excluir")
    public String deletar(@RequestParam("id") Integer id) {
        produtoService.excluir(id);
        return "redirect:/produto/lista";
    }

    @GetMapping(value = "/produto/lista")
    public String obterListaProduto(Model model) {
        model.addAttribute("titulo", "Lista de Produtos");
        model.addAttribute("rota", "produto");
        model.addAttribute("listagem", produtoService.obterLista());
        model.addAttribute("campoDeBusca", "codigo");
        return appController.showHome(model);
    }

    @GetMapping(value = "/produto/pesquisar")
    public String pesquisar(Model model, @RequestParam("campoBusca") Integer campoBusca) {
        var resultado = produtoService.pesquisar(campoBusca);
        if (nonNull(resultado)) {
            model.addAttribute("objeto", resultado);
            return appController.showHome(model);
        }
        return "redirect:/produto/lista";
    }

}
