package br.edu.infinet.vendasapi.vendasapi.model.service;

import br.edu.infinet.vendasapi.vendasapi.model.domain.Informacao;
import br.edu.infinet.vendasapi.vendasapi.model.repository.InformacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InformacaoService {

    private final InformacaoRepository informacaoRepository;

    public InformacaoService(InformacaoRepository informacaoRepository) {
        this.informacaoRepository = informacaoRepository;
    }

    public List<Informacao> obterLista() {

        return informacaoRepository.findAll();
    }

    public Informacao incluir(Informacao informacao) {

        return informacaoRepository.save(informacao);
    }
}
