package br.edu.infinet.vendasapi.vendasapi.controller;

import br.edu.infinet.vendasapi.vendasapi.model.domain.Informacao;
import br.edu.infinet.vendasapi.vendasapi.model.service.InformacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/api/vendas")
public class AppController {

    @Autowired
    private InformacaoService informacaoService;

    @GetMapping(value = "/lista")
    public List<Informacao> info() {
        return informacaoService.obterLista();
    }

    @PostMapping(value = "/incluir")
    public Informacao incluir(@RequestBody Informacao informacao) {
        return informacaoService.incluir(informacao);
    }
}
