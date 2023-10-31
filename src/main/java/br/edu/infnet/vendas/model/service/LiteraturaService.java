package br.edu.infnet.vendas.model.service;

import br.edu.infnet.vendas.model.domain.Literatura;
import br.edu.infnet.vendas.model.repository.LiteraturaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LiteraturaService {

    private final LiteraturaRepository literaturaRepository;

    public LiteraturaService(LiteraturaRepository literaturaRepository) {
        this.literaturaRepository = literaturaRepository;
    }


    public void incluir(Literatura literatura) {
        literaturaRepository.save(literatura);
    }


    public List<Literatura> obterLista() {
        return literaturaRepository.findAll();
    }
}
