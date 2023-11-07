package br.edu.infnet.vendas.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "literaturas")
public class Literatura extends Produto {

    @Size(min = 1, max = 255, message = "O campo autor deve ter entre {min} e {max} caracteres")
    private String autor;

    @Min(value = 1, message = "O campo número de páginas deve ser maior que 0")
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
        return super.toString() + " - " + autor + " - " + numeroDePaginas ;
    }
}
