package com.workforce;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Principal {

    private static final BigDecimal SALARIO_MINIMO = new BigDecimal("1212.00");

    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

        List<Funcionario> funcionarios = cadastrarFuncionarios();
        removerJoao(funcionarios);

        System.out.println("3.3 - Lista de todos os funcionários");
        imprimirFuncionarios(funcionarios);

        aumentarSalarios(funcionarios);

        Map<String, List<Funcionario>> porFuncao = agruparPorFuncao(funcionarios);
        System.out.println("3.6 - Funcionários agrupados por função");
        imprimirAgrupados(porFuncao);

        System.out.println("3.8 - Funcionários que fazem aniversário em outubro ou dezembro");
        imprimirAniversariantes(funcionarios);

        System.out.println("3.9 - Funcionário com maior idade");
        imprimirMaisVelho(funcionarios);

        System.out.println("3.10 - Funcionários em ordem alfabética");
        imprimirOrdemAlfabetica(funcionarios);

        System.out.println("3.11 - Soma total dos salários");
        imprimirSomaSalarios(funcionarios);

        System.out.println("3.12 - Quantidade de salários mínimos por funcionário");
        imprimirSalariosMinimos(funcionarios);
    }

    private static List<Funcionario> cadastrarFuncionarios() {
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 18), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));
        return funcionarios;
    }

    private static void removerJoao(List<Funcionario> funcionarios) {
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));
    }

    private static void imprimirFuncionarios(List<Funcionario> funcionarios) {
        for (Funcionario funcionario : funcionarios) {
            System.out.println(funcionario);
        }
    }

    private static void aumentarSalarios(List<Funcionario> funcionarios) {
        for (Funcionario funcionario : funcionarios) {
            funcionario.aumentarSalario(new BigDecimal("0.10"));
        }
    }

    private static Map<String, List<Funcionario>> agruparPorFuncao(List<Funcionario> funcionarios) {
        return funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao, LinkedHashMap::new, Collectors.toList()));
    }

    private static void imprimirAgrupados(Map<String, List<Funcionario>> porFuncao) {
        for (Map.Entry<String, List<Funcionario>> entrada : porFuncao.entrySet()) {
            System.out.println(entrada.getKey() + ":");
            imprimirFuncionarios(entrada.getValue());
        }
    }

    private static void imprimirAniversariantes(List<Funcionario> funcionarios) {
        List<Funcionario> aniversariantes = new ArrayList<>();
        for (Funcionario funcionario : funcionarios) {
            int mes = funcionario.getDataNascimento().getMonthValue();
            if (mes == 10 || mes == 12) {
                aniversariantes.add(funcionario);
            }
        }
        imprimirFuncionarios(aniversariantes);
    }

    private static void imprimirMaisVelho(List<Funcionario> funcionarios) {
        Funcionario maisVelho = Collections.min(funcionarios, Comparator.comparing(Funcionario::getDataNascimento));
        System.out.println("Nome: " + maisVelho.getNome() + " | Idade: " + maisVelho.getIdade());
    }

    private static void imprimirOrdemAlfabetica(List<Funcionario> funcionarios) {
        List<Funcionario> ordenados = new ArrayList<>(funcionarios);
        ordenados.sort(Comparator.comparing(Funcionario::getNome));
        imprimirFuncionarios(ordenados);
    }

    private static void imprimirSomaSalarios(List<Funcionario> funcionarios) {
        BigDecimal total = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println(Formatador.formatarValor(total));
    }

    private static void imprimirSalariosMinimos(List<Funcionario> funcionarios) {
        for (Funcionario funcionario : funcionarios) {
            BigDecimal salariosMinimos = funcionario.calcularSalariosMinimos(SALARIO_MINIMO);
            System.out.println(funcionario.getNome() + ": " + Formatador.formatarValor(salariosMinimos) + " salários mínimos");
        }
    }
}
