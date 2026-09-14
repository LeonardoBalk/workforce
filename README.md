# Workforce

Cadastro de funcionários de uma indústria, feito em Java puro. Cobre inserção, remoção, reajuste salarial, agrupamento por função, filtros por data de nascimento, ordenação e cálculos sobre a folha de pagamento.

A saída do programa é organizada por etapas, permitindo acompanhar cada operação diretamente pelo console.

## Estrutura

- `Pessoa`: nome, data de nascimento e cálculo de idade.
- `Funcionario`: estende `Pessoa`, adiciona salário e função, com as regras de reajuste e conversão pra salários mínimos.
- `Formatador`: formata datas (dd/MM/yyyy) e valores numéricos no padrão pt-BR.
- `Principal`: executa as operações em sequência.

## Pré-requisitos

- JDK 17 ou superior
- Maven 3.8 ou superior

## Build

```
mvn compile
```

## Executar

```
mvn compile exec:java
```

Ou gerar o jar e rodar direto:

```
mvn package
java -jar target/workforce.jar
```

## Acentos aparecendo errado no terminal

No Windows, o console pode não estar configurado para UTF-8 por padrão. Se nomes e palavras acentuadas saírem ilegíveis, rode antes de executar:

```powershell
chcp 65001
[Console]::OutputEncoding = [System.Text.Encoding]::UTF8
```
