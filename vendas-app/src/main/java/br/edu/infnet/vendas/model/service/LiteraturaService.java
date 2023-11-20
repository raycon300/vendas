package br.edu.infnet.vendas.model.service;

import br.edu.infnet.vendas.model.domain.Literatura;
import br.edu.infnet.vendas.model.repository.LiteraturaRepository;
import org.springframework.data.domain.Sort;
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

        return literaturaRepository.findAll(Sort.by(Sort.Direction.ASC, "autor"));
    }

    public long obterQuantidade() {
        return literaturaRepository.count();
    }

    public void excluir(Integer id) {
        literaturaRepository.deleteById(id);
    }

    public Literatura pesquisar(String campoBusca) {
        return literaturaRepository.findByAutor(campoBusca);
    }
}
