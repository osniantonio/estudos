# **Exemplos de Uso de `Optional` em Java**

Este repositório contém um exemplo prático de como utilizar a classe `Optional` em Java, juntamente com a estrutura de um aplicativo Spring Boot simples. O objetivo principal é demonstrar boas práticas e técnicas para evitar exceções de ponteiro nulo (`NullPointerException`), reduzir a verbosidade do código e melhorar a legibilidade.

## **Estrutura do Projeto**

O projeto é uma aplicação Spring Boot com Maven, com foco na demonstração do uso de `Optional` em diferentes cenários. Ele contém as seguintes partes principais:

- **Entidades** (`br.com.gavb.entities`): Representações dos dados que serão processados (por exemplo, `AddressInput` e `PersonInput`).
- **Mappers** (`br.com.gavb.mappers`): Classes para mapear objetos de entrada para entidades de domínio.
- **Serviços** (`br.com.gavb.services`): Contêm a lógica de negócios que processa as entradas e aplica as regras de negócio.
- **Dependências Maven**: Gerenciamento das dependências através do arquivo `pom.xml`.

## **Como Rodar o Projeto**

1. Clone este repositório para sua máquina local:

    ```bash
    git clone https://github.com/osniantonio/estudos.git
    cd java/javaoptional
    ```

2. Compile e rode o projeto com Maven:

    ```bash
    mvn spring-boot:run
    ```

3. A aplicação estará rodando localmente, e você pode observar o processamento das entidades no console.

## **Conceito de `Optional` em Java**

`Optional` é uma classe contêiner que pode conter ou não um valor. Seu principal benefício é evitar o uso de valores nulos (que podem causar exceções de ponteiro nulo - `NullPointerException`). Além disso, `Optional` ajuda a reduzir a verbosidade do código, tornando-o mais funcional e fácil de entender.

### **Principais Métodos Usados no Projeto**

- **`map()`**:
    - Transforma o valor dentro de um `Optional` se ele não for `null`. Caso contrário, retorna um `Optional.empty()`.
    - **Exemplo**: `optionalAddress.map(AddressMapper::toEntity)`

- **`orElse()`**:
    - Retorna o valor dentro do `Optional` se presente, caso contrário, retorna o valor default fornecido.
    - **Exemplo**: `optionalPerson.orElse("Pessoa não disponível")`

- **`ifPresentOrElse()`**:
    - Executa uma ação se o valor do `Optional` estiver presente, ou uma ação alternativa se o valor estiver ausente.
    - **Exemplo**: `optionalAddress.ifPresentOrElse(address -> {...}, () -> {...})`

- **`filter()`**:
    - Filtra o valor dentro do `Optional` baseado em uma condição. Se o valor não corresponder à condição, o `Optional` se torna vazio.
    - **Exemplo**: `optionalPerson.filter(person -> person.getPersonName() != null)`

- **`orElseGet()`**:
    - Similar ao `orElse()`, mas aceita um **Supplier** que fornece o valor default de forma preguiçosa (só é calculado quando necessário).
    - **Exemplo**: `optionalPerson.orElseGet(() -> "Nome não informado")`

- **`flatMap()`**:
    - Usado quando o valor dentro do `Optional` é outro `Optional`, permitindo "desempacotar" o valor.
    - **Exemplo**: `optionalPerson.flatMap(person -> Optional.ofNullable(person.getPersonName()))`
