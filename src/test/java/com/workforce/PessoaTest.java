package com.workforce;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PessoaTest {

    @Test
    void calculaIdadeNoDiaAnteriorAoAniversario() {
        Pessoa pessoa = new Pessoa("Maria", LocalDate.of(2000, 10, 18));

        assertEquals(25, pessoa.getIdade(LocalDate.of(2026, 10, 17)));
    }

    @Test
    void incrementaIdadeNoDiaDoAniversario() {
        Pessoa pessoa = new Pessoa("Maria", LocalDate.of(2000, 10, 18));

        assertEquals(26, pessoa.getIdade(LocalDate.of(2026, 10, 18)));
    }
}
