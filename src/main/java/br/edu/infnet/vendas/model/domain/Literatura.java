package br.edu.infnet.vendas.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "literaturas")
public class Literatura extends Produto {

    private String autor;
    private int numeroDePaginas;

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getNumeroDePaginas() {
        return numeroDePaginas;
    }

    public void setNumeroDePaginas(int numeroDePaginas) {
        this.numeroDePaginas = numeroDePaginas;
    }

    @Override
    public String toString() {
        return "Livro{" + "autor=" + autor + ", numeroDePaginas=" + numeroDePaginas + ", " + super.toString() + '}';
    }
}
