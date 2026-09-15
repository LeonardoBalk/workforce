package com.workforce;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class PrincipalTest {

    @Test
    void cadastraOsDezFuncionariosNaOrdemDaTabela() {
        List<DadosEsperados> esperados = List.of(
                new DadosEsperados("Maria", "2000-10-18", "2009.44", "Operador"),
                new DadosEsperados("João", "1990-05-12", "2284.38", "Operador"),
                new DadosEsperados("Caio", "1961-05-02", "9836.14", "Coordenador"),
                new DadosEsperados("Miguel", "1988-10-14", "19119.88", "Diretor"),
                new DadosEsperados("Alice", "1995-01-05", "2234.68", "Recepcionista"),
                new DadosEsperados("Heitor", "1999-11-19", "1582.72", "Operador"),
                new DadosEsperados("Arthur", "1993-03-31", "4071.84", "Contador"),
                new DadosEsperados("Laura", "1994-07-18", "3017.45", "Gerente"),
                new DadosEsperados("Heloísa", "2003-05-24", "1606.85", "Eletricista"),
                new DadosEsperados("Helena", "1996-09-02", "2799.93", "Gerente"));

        List<Funcionario> funcionarios = Principal.cadastrarFuncionarios();

        assertEquals(esperados.size(), funcionarios.size());
        for (int i = 0; i < esperados.size(); i++) {
            DadosEsperados esperado = esperados.get(i);
            Funcionario funcionario = funcionarios.get(i);
            assertAll("Dados de " + esperado.nome(),
                    () -> assertEquals(esperado.nome(), funcionario.getNome()),
                    () -> assertEquals(LocalDate.parse(esperado.nascimento()), funcionario.getDataNascimento()),
                    () -> assertEquals(new BigDecimal(esperado.salario()), funcionario.getSalario()),
                    () -> assertEquals(esperado.funcao(), funcionario.getFuncao()));
        }
    }

    @Test
    void removeJoaoDaLista() {
        List<Funcionario> funcionarios = Principal.cadastrarFuncionarios();

        Principal.removerJoao(funcionarios);

        assertEquals(9, funcionarios.size());
        assertFalse(funcionarios.stream().anyMatch(funcionario -> funcionario.getNome().equals("João")));
        assertEquals(List.of("Maria", "Caio", "Miguel", "Alice", "Heitor", "Arthur", "Laura", "Heloísa", "Helena"),
                funcionarios.stream().map(Funcionario::getNome).toList());
    }

    @Test
    void agrupaTodosOsFuncionariosPorFuncaoAposRemoverJoao() {
        List<Funcionario> funcionarios = Principal.cadastrarFuncionarios();
        Principal.removerJoao(funcionarios);

        Map<String, List<Funcionario>> porFuncao = Principal.agruparPorFuncao(funcionarios);

        Map<String, List<String>> gruposEsperados = Map.of(
                "Operador", List.of("Maria", "Heitor"),
                "Coordenador", List.of("Caio"),
                "Diretor", List.of("Miguel"),
                "Recepcionista", List.of("Alice"),
                "Contador", List.of("Arthur"),
                "Gerente", List.of("Laura", "Helena"),
                "Eletricista", List.of("Heloísa"));

        assertEquals(gruposEsperados.keySet(), porFuncao.keySet());
        assertEquals(List.of("Operador", "Coordenador", "Diretor", "Recepcionista", "Contador", "Gerente", "Eletricista"),
                new ArrayList<>(porFuncao.keySet()));
        for (Map.Entry<String, List<String>> grupo : gruposEsperados.entrySet()) {
            assertEquals(grupo.getValue(),
                    porFuncao.get(grupo.getKey()).stream().map(Funcionario::getNome).toList(),
                    "Integrantes de " + grupo.getKey());
        }
    }

    @Test
    void filtraAniversariantesDeOutubroEDezembro() {
        Funcionario nascidoEmOutubro = new Funcionario("Outubro", LocalDate.of(1990, 10, 5), new BigDecimal("2000"), "Operador");
        Funcionario nascidoEmDezembro = new Funcionario("Dezembro", LocalDate.of(1990, 12, 20), new BigDecimal("2000"), "Operador");
        Funcionario nascidoEmMarco = new Funcionario("Março", LocalDate.of(1990, 3, 15), new BigDecimal("2000"), "Operador");
        List<Funcionario> funcionarios = List.of(nascidoEmOutubro, nascidoEmDezembro, nascidoEmMarco);

        List<Funcionario> aniversariantes = Principal.filtrarAniversariantes(funcionarios);

        assertEquals(List.of(nascidoEmOutubro, nascidoEmDezembro), aniversariantes);
    }

    @Test
    void encontraMariaEMiguelComoAniversariantesDaTabela() {
        List<Funcionario> funcionarios = Principal.cadastrarFuncionarios();
        Principal.removerJoao(funcionarios);

        List<Funcionario> aniversariantes = Principal.filtrarAniversariantes(funcionarios);

        assertEquals(List.of("Maria", "Miguel"), aniversariantes.stream().map(Funcionario::getNome).toList());
    }

    @Test
    void ordenaPorNomeSemAlterarAListaOriginal() {
        List<Funcionario> funcionarios = Principal.cadastrarFuncionarios();
        Principal.removerJoao(funcionarios);
        List<Funcionario> original = new ArrayList<>(funcionarios);

        List<Funcionario> ordenados = Principal.ordenarPorNome(funcionarios);

        assertEquals(List.of("Alice", "Arthur", "Caio", "Heitor", "Helena", "Heloísa", "Laura", "Maria", "Miguel"),
                ordenados.stream().map(Funcionario::getNome).toList());
        assertEquals(original, funcionarios);
    }

    @Test
    void somaOsSalariosAposRemoverJoaoEAplicarReajuste() {
        List<Funcionario> funcionarios = Principal.cadastrarFuncionarios();
        Principal.removerJoao(funcionarios);
        Principal.aumentarSalarios(funcionarios);

        BigDecimal total = Principal.somarSalarios(funcionarios);

        assertEquals(new BigDecimal("50906.82"), total);
    }

    @Test
    void encontraCaioComoOFuncionarioMaisVelho() {
        List<Funcionario> funcionarios = Principal.cadastrarFuncionarios();

        Funcionario maisVelho = Principal.encontrarMaisVelho(funcionarios);

        assertEquals("Caio", maisVelho.getNome());
    }

    private record DadosEsperados(String nome, String nascimento, String salario, String funcao) {
    }
}
