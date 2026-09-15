package com.workforce;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FuncionarioTest {

    @Test
    void aumentaSalarioEmDezPorCento() {
        Funcionario funcionario = new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador");

        funcionario.aumentarSalario(new BigDecimal("0.10"));

        assertEquals(new BigDecimal("2210.38"), funcionario.getSalario());
    }

    @Test
    void aumentaSalarioArredondandoFracaoDeCentavo() {
        Funcionario funcionario = new Funcionario("Teste", LocalDate.of(1990, 1, 1), new BigDecimal("1000.15"), "Operador");

        funcionario.aumentarSalario(new BigDecimal("0.10"));

        assertEquals(new BigDecimal("1100.17"), funcionario.getSalario());
    }

    @Test
    void calculaQuantidadeDeSalariosMinimos() {
        Funcionario funcionario = new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2210.38"), "Operador");

        BigDecimal salariosMinimos = funcionario.calcularSalariosMinimos(new BigDecimal("1212.00"));

        assertEquals(new BigDecimal("1.82"), salariosMinimos);
    }
}
