package br.edu.infnet.vendas.model.service;

import br.edu.infnet.vendas.model.domain.Literatura;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LiteraturaService {

    private final Map<Integer, Literatura> livros = new HashMap<>();


    public void incluir(Literatura literatura) {
        livros.putIfAbsent(literatura.getCodigo(), literatura);
    }


    public List<Literatura> obterLista() {
        return new ArrayList<>(livros.values());
    }
}
