package br.edu.infinet.vendasapi.vendasapi.model.repository;

import br.edu.infinet.vendasapi.vendasapi.model.domain.Informacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface InformacaoRepository extends JpaRepository<Informacao, Integer> {
}
