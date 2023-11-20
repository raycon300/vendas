package br.edu.infnet.vendas.model.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

import java.util.ArrayList;
import java.util.List;

import static java.util.Optional.ofNullable;

@Entity
@Table(name = "vendedores",
uniqueConstraints = {
        @UniqueConstraint(name = "uk_vendedor", columnNames = {"cpf", "email"})
})
public class Vendedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$", message = "O campo cpf deve ser um cpf válido")
    private String cpf;

    @Email(regexp = "^[a-zA-Z0-9._]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "O campo email deve ser um email válido")
    private String email;

    @Embedded
    private Endereco endereco;

    @OneToMany(mappedBy = "vendedor", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Produto> produtos;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return id + " - " + nome + " - " + cpf +" - " + email + " - " + endereco + " - " + ofNullable(produtos).orElseGet(ArrayList::new).size();
    }
}
