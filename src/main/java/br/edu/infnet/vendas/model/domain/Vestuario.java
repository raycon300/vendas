package br.edu.infnet.vendas.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "vestuarios")
public class Vestuario extends Produto {

    @NotBlank(message = "O campo tamanho é obrigatório")
    private String tamanho;
    private String cor;

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    @Override
    public String toString() {
        return super.toString() + " - " + tamanho + " - " + cor + " - ";
    }
}

