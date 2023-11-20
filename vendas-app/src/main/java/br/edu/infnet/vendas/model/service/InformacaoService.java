package br.edu.infnet.vendas.model.service;

import br.edu.infnet.vendas.clients.InformacaoClient;
import br.edu.infnet.vendas.model.domain.Informacao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InformacaoService {
    private final InformacaoClient informacaoClient;

    public InformacaoService(InformacaoClient informacaoClient) {
        this.informacaoClient = informacaoClient;
    }

    public List<Informacao> obterLista() {
        return informacaoClient.obterLista();
    }

    public Informacao incluir(Informacao informacao) {
        return informacaoClient.incluir(informacao);
    }
}
