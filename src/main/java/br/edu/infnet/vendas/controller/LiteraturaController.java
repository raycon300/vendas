package br.edu.infnet.vendas.controller;

import br.edu.infnet.vendas.model.service.LiteraturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LiteraturaController {

    @Autowired
    private LiteraturaService literaturaService;

    @PostMapping(value = "/literatura/excluir")
    public String deletar(@RequestParam("id") Integer id) {
        literaturaService.excluir(id);
        return "redirect:/literatura/lista";
    }

}
