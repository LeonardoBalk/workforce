package com.workforce;

import java.time.LocalDate;
import java.time.Period;

public class Pessoa {

    private final String nome;
    private final LocalDate dataNascimento;

    public Pessoa(String nome, LocalDate dataNascimento) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public int getIdade() {
        return getIdade(LocalDate.now());
    }

    public int getIdade(LocalDate referencia) {
        return Period.between(dataNascimento, referencia).getYears();
    }
}
