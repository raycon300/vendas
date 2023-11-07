package br.edu.infnet.vendas.model.service;

import br.edu.infnet.vendas.model.domain.Produto;
import br.edu.infnet.vendas.model.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }


    public void incluir(Produto produto) {
        produtoRepository.save(produto);
    }


    public List<Produto> obterLista() {
        return produtoRepository.findAll();
    }

    public Boolean isProdutoNaoCadastrado(String descricao){
        return produtoRepository.findByDescricao(descricao).isEmpty();
    }

    public List<Produto> obterLista(Integer id) {
        return produtoRepository.obterLista(id);
    }

    public long obterQuantidade() {
        return produtoRepository.count();
    }

    public void excluir(Integer id) {
        produtoRepository.deleteById(id);
    }
}
