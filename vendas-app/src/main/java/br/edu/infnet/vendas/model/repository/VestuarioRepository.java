package br.edu.infnet.vendas.model.repository;

import br.edu.infnet.vendas.model.domain.Vestuario;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VestuarioRepository extends CrudRepository<Vestuario, Integer> {
    Vestuario findByDescricao(String campoBusca);

    List<Vestuario> findAll(Sort sort);
}
