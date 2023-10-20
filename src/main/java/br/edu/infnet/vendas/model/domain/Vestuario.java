package br.edu.infnet.vendas.model.domain;

public class Vestuario extends Produto {
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
        return "Roupa{" +
                "tamanho='" + tamanho + '\'' +
                ", cor='" + cor + '\'' +
                ", " + super.toString() +
                '}';
    }
}

