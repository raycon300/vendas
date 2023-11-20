package br.edu.infnet.vendas.controller;

import br.edu.infnet.vendas.model.service.VestuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static java.util.Objects.nonNull;

@Controller
public class VestuarioController {

    @Autowired
    private VestuarioService vestuarioService;
    @Autowired
    private AppController appController;

    @PostMapping(value = "/vestuario/excluir")
    public String deletar(@RequestParam("id") Integer id) {
        vestuarioService.excluir(id);
        return "redirect:/vestuario/lista";
    }

    @GetMapping(value = "/vestuario/lista")
    public String obterListaVestuario(Model model) {
        model.addAttribute("titulo", "Lista de Vestuários");
        model.addAttribute("rota", "vestuario");
        model.addAttribute("listagem", vestuarioService.obterLista());
        model.addAttribute("campoDeBusca", "descricao");
        return appController.showHome(model);
    }

    @GetMapping(value = "/vestuario/pesquisar")
    public String pesquisar(Model model, @RequestParam("campoBusca") String campoBusca) {
        var resultado = vestuarioService.pesquisar(campoBusca);
        if (nonNull(resultado)) {
            model.addAttribute("objeto", resultado);
            return appController.showHome(model);
        }
        return "redirect:/produto/lista";
    }

}
