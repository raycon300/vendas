package br.edu.infnet.vendas.controller;

import br.edu.infnet.vendas.model.service.LiteraturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static java.util.Objects.nonNull;

@Controller
public class LiteraturaController {

    @Autowired
    private LiteraturaService literaturaService;
    @Autowired
    private AppController appController;

    @PostMapping(value = "/literatura/excluir")
    public String deletar(@RequestParam("id") Integer id) {
        literaturaService.excluir(id);
        return "redirect:/literatura/lista";
    }

    @GetMapping(value = "/literatura/lista")
    public String obterListaLiteratura(Model model) {
        model.addAttribute("titulo", "Lista de Literaturas");
        model.addAttribute("rota", "literatura");
        model.addAttribute("listagem", literaturaService.obterLista());
        model.addAttribute("campoDeBusca", "autor");
        return appController.showHome(model);
    }

    @GetMapping(value = "/literatura/pesquisar")
    public String pesquisar(Model model, @RequestParam("campoBusca") String campoBusca) {
        var resultado = literaturaService.pesquisar(campoBusca);
        if (nonNull(resultado)) {
            model.addAttribute("objeto", resultado);
            return appController.showHome(model);
        }
        return "redirect:/literatura/lista";
    }

}
