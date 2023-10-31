package br.edu.infnet.vendas.model.repository;

import br.edu.infnet.vendas.model.domain.Vestuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VestuarioRepository extends JpaRepository<Vestuario, Integer> {
}
