package br.edu.infnet.vendas.model.service;

import br.edu.infnet.vendas.model.domain.Vendedor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VendedorService {

    private final Map<String, Vendedor> vendedores = new HashMap<>();


    public void incluir(Vendedor vendedor) {
        vendedores.putIfAbsent(vendedor.getCpf(), vendedor);
    }


    public List<Vendedor> obterLista() {
        return new ArrayList<>(vendedores.values());
    }
}
