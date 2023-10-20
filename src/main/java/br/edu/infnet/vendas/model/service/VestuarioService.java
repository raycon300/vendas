package br.edu.infnet.vendas.model.service;

import br.edu.infnet.vendas.model.domain.Vestuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VestuarioService {

    private final Map<Integer, Vestuario> roupas = new HashMap<>();


    public void incluir(Vestuario vestuario) {
        roupas.putIfAbsent(vestuario.getCodigo(), vestuario);
    }


    public List<Vestuario> obterLista() {
        return new ArrayList<>(roupas.values());
    }
}
