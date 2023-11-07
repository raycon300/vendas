package br.edu.infnet.vendas.controller;

import br.edu.infnet.vendas.model.service.VestuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class VestuarioController {

    @Autowired
    private VestuarioService vestuarioService;

    @PostMapping(value = "/vestuario/excluir")
    public String deletar(@RequestParam("id") Integer id) {
        vestuarioService.excluir(id);
        return "redirect:/vestuario/lista";
    }

}
