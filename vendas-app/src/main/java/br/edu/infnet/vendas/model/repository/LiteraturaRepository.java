package br.edu.infnet.vendas.model.repository;

import br.edu.infnet.vendas.model.domain.Literatura;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LiteraturaRepository extends CrudRepository<Literatura, Integer> {
    Literatura findByAutor(String campoBusca);

    List<Literatura> findAll(Sort sort);
}
