package br.edu.infnet.vendas.clients;

import br.edu.infnet.vendas.model.domain.Informacao;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "informacaoClient", url = "http://localhost:8081/api/vendas")
public interface InformacaoClient {

    @GetMapping(value = "/lista")
    List<Informacao> obterLista();

    @PostMapping(value = "/incluir")
    Informacao incluir(Informacao informacao);
}
