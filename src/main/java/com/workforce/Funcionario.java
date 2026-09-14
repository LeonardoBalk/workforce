package com.workforce;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class Funcionario extends Pessoa {

    private final String funcao;
    private BigDecimal salario;

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void aumentarSalario(BigDecimal percentual) {
        salario = salario.add(salario.multiply(percentual)).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal calcularSalariosMinimos(BigDecimal salarioMinimo) {
        return salario.divide(salarioMinimo, 2, RoundingMode.HALF_UP);
    }

    @Override
    public String toString() {
        return getNome()
                + " | Nascimento: " + Formatador.formatarData(getDataNascimento())
                + " | Salário: " + Formatador.formatarValor(salario)
                + " | Função: " + funcao;
    }
}
