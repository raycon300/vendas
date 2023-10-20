package br.edu.infnet.vendas.model.service;

import br.edu.infnet.vendas.model.domain.Produto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProdutoService {

    private final Map<Integer, Produto> produtos = new HashMap<>();


    public void incluir(Produto produto) {
        produtos.putIfAbsent(produto.getCodigo(), produto);
    }


    public List<Produto> obterLista() {
        return new ArrayList<>(produtos.values());
    }
}
