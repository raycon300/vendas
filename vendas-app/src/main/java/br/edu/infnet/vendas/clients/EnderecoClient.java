package br.edu.infnet.vendas.clients;


import br.edu.infnet.vendas.model.domain.Endereco;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "enderecoClient", url = "https://viacep.com.br/ws")
public interface EnderecoClient {

    @GetMapping("/{cep}/json")
    Endereco buscarCep(@PathVariable String cep);
}
