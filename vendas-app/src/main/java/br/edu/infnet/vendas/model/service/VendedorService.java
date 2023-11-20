package br.edu.infnet.vendas.model.service;

import br.edu.infnet.vendas.clients.EnderecoClient;
import br.edu.infnet.vendas.model.domain.Vendedor;
import br.edu.infnet.vendas.model.repository.VendedorRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendedorService {

    private final VendedorRepository vendedorRepository;
    private final EnderecoClient enderecoClient;

    public VendedorService(VendedorRepository vendedorRepository, EnderecoClient enderecoClient) {
        this.enderecoClient = enderecoClient;
        this.vendedorRepository = vendedorRepository;
    }


    public void incluir(Vendedor vendedor) {
        vendedor.setEndereco(enderecoClient.buscarCep(vendedor.getEndereco().getCep()));
        vendedorRepository.save(vendedor);
    }

    public Vendedor pesquisar(String paramBusca) {
        return vendedorRepository.findByCpf(paramBusca);
    }

    public List<Vendedor> obterLista() {
        return vendedorRepository.findAll(Sort.by(Sort.Direction.ASC, "nome"));
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
