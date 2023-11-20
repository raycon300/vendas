package br.edu.infinet.vendasapi.vendasapi.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Informacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String campo;
    private String descricao;

    public Informacao(Integer id, String campo, String descricao) {
        this();
        this.id = id;
        this.campo = campo;
        this.descricao = descricao;
    }

    public Informacao() {
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {

        return String.format("id (%d) - campo (%s) - descricao (%s)",
                id, campo, descricao
        );
    }
}
