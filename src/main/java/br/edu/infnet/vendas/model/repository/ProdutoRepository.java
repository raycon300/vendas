package br.edu.infnet.vendas.model.repository;

import br.edu.infnet.vendas.model.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    Optional<Produto> findByDescricao(String descricao);

    @Query("from Produto p where p.vendedor.id = :idVendedor")
    List<Produto> obterLista(Integer idVendedor);
}
