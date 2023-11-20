package br.edu.infnet.vendas.model.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Endereco {

    @Column( name = "cep" )
    private String cep;

    @Column( name = "logradouro" )
    private String logradouro;

    @Column( name = "bairro" )
    private String bairro;

    @Column( name = "uf" )
    private String uf;

    public Endereco() {
    }

    public Endereco(String cep) {
        this();
        this.cep = cep;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    @Override
    public String toString() {
        return  cep + " - " + logradouro + " - " + bairro + " - " + uf ;
    }
}
