package br.edu.infnet.vendas.model.repository;

import br.edu.infnet.vendas.model.domain.Produto;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends CrudRepository<Produto, Integer> {

    Optional<Produto> findByDescricao(String descricao);

    @Query("from Produto p where p.vendedor.id = :idVendedor")
    List<Produto> obterLista(Integer idVendedor);

    Produto findByCodigo(Integer campoBusca);

    List<Produto> findAll(Sort sort);

}
