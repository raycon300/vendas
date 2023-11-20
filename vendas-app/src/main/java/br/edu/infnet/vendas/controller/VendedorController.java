package br.edu.infnet.vendas.controller;

import br.edu.infnet.vendas.model.service.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static java.util.Objects.nonNull;

@Controller
public class VendedorController {

    @Autowired
    private VendedorService vendedorService;
    @Autowired
    private AppController appController;

    @PostMapping(value = "/vendedor/excluir")
    public String deletar(@RequestParam("id") Integer id) {
        vendedorService.excluir(id);
        return "redirect:/vendedor/lista";
    }

    @GetMapping(value = "/vendedor/lista")
    public String obterListaVendedor(Model model) {
        model.addAttribute("titulo", "Lista de Vendedores");
        model.addAttribute("rota", "vendedor");
        model.addAttribute("listagem", vendedorService.obterLista());
        model.addAttribute("campoDeBusca", "cpf");
        return appController.showHome(model);
    }

    @GetMapping(value = "/vendedor/pesquisar")
    public String pesquisar(Model model, @RequestParam("campoBusca") String campoBusca) {
        var resultado = vendedorService.pesquisar(campoBusca);
        if (nonNull(resultado)) {
            model.addAttribute("objeto", resultado);
            return appController.showHome(model);
        }
        return "redirect:/vendedor/lista";
    }
}
