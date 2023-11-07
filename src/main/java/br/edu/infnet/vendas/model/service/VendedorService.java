package br.edu.infnet.vendas.model.service;

import br.edu.infnet.vendas.model.domain.Vendedor;
import br.edu.infnet.vendas.model.repository.VendedorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendedorService {

    private final VendedorRepository vendedorRepository;

    public VendedorService(VendedorRepository vendedorRepository) {
        this.vendedorRepository = vendedorRepository;
    }


    public void incluir(Vendedor vendedor) {
        vendedorRepository.save(vendedor);
    }


    public List<Vendedor> obterLista() {
        return vendedorRepository.findAll();
    }

    public Vendedor findById(Integer id) {
        return vendedorRepository.findById(id).orElseThrow();
    }

    public long obterQuantidade() {
        return vendedorRepository.count();
    }

    public void excluir(Integer id) {
        vendedorRepository.deleteById(id);
    }
}
