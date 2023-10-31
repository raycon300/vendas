package br.edu.infnet.vendas.model.repository;

import br.edu.infnet.vendas.model.domain.Literatura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LiteraturaRepository extends JpaRepository<Literatura, Integer> {
}
