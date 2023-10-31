package br.edu.infnet.vendas.model.repository;

import br.edu.infnet.vendas.model.domain.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendedorRepository extends JpaRepository<Vendedor, Integer> {
}
