package br.edu.infnet.vendas.model.repository;

import br.edu.infnet.vendas.model.domain.Vendedor;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VendedorRepository extends CrudRepository<Vendedor, Integer> {

    Vendedor findByCpf(String cpf);

    List<Vendedor> findAll(Sort sort);
}
