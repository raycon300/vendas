package br.edu.infnet.vendas.controller;

import br.edu.infnet.vendas.model.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping(value = "/produto/excluir")
    public String deletar(@RequestParam("id") Integer id) {
        produtoService.excluir(id);
        return "redirect:/produto/lista";
    }

}
