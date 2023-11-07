package br.edu.infnet.vendas.model.service;

import br.edu.infnet.vendas.model.domain.Vestuario;
import br.edu.infnet.vendas.model.repository.VestuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VestuarioService {

    private final VestuarioRepository vestuarioRepository;

    public VestuarioService(VestuarioRepository vestuarioRepository) {
        this.vestuarioRepository = vestuarioRepository;
    }


    public void incluir(Vestuario vestuario) {
        vestuarioRepository.save(vestuario);
    }


    public List<Vestuario> obterLista() {
        return vestuarioRepository.findAll();
    }

    public long obterQuantidade() {
        return vestuarioRepository.count();
    }

    public void excluir(Integer id) {
        vestuarioRepository.deleteById(id);
    }
}
