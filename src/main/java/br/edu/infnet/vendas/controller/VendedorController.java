package br.edu.infnet.vendas.controller;

import br.edu.infnet.vendas.model.service.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class VendedorController {

    @Autowired
    private VendedorService vendedorService;

    @PostMapping(value = "/vendedor/excluir")
    public String deletar(@RequestParam("id") Integer id) {
        vendedorService.excluir(id);
        return "redirect:/vendedor/lista";
    }

}
