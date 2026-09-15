package com.workforce;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FormatadorTest {

    @Test
    void formataDataNoPadraoBrasileiro() {
        assertEquals("18/10/2000", Formatador.formatarData(LocalDate.of(2000, 10, 18)));
    }

    @Test
    void formataDataComZerosAEsquerdaNoDiaEMes() {
        assertEquals("05/01/1995", Formatador.formatarData(LocalDate.of(1995, 1, 5)));
    }

    @Test
    void formataValorComUmaCasaDecimalCompletandoComZero() {
        assertEquals("2.009,40", Formatador.formatarValor(new BigDecimal("2009.4")));
    }

    @Test
    void formataValorComSeparadorDeMilhar() {
        assertEquals("50.906,82", Formatador.formatarValor(new BigDecimal("50906.82")));
    }

    @Test
    void formataValorArredondandoMeioCentavoParaCima() {
        assertEquals("1.100,17", Formatador.formatarValor(new BigDecimal("1100.165")));
    }
}
