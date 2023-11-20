package br.edu.infnet.vendas.model.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "produtos")
@Inheritance(strategy = InheritanceType.JOINED)
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "O campo descrição é obrigatório")
    private String descricao;

    @PositiveOrZero(message = "O campo código deve ser maior ou igual a 0")
    private int codigo;

    @Positive(message = "O campo preço deve ser maior que 0")
    private float preco;
    private boolean estoque;

    @ManyToOne
    @NotNull(message = "O campo vendedor é obrigatório")
    private Vendedor vendedor;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public boolean isEstoque() {
        return estoque;
    }

    public void setEstoque(boolean estoque) {
        this.estoque = estoque;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    @Override
    public String toString() {
        return  id + " - " + descricao + " - " + codigo + " - " + preco + " - " + estoque + " - " + vendedor;
    }
}
