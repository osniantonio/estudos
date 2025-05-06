# Overview e Guia de Uso — java.util.Optional
O `java.util.Optional` foi introduzido no Java 8 como parte de um conjunto de novas funcionalidades, incluindo expressões lambda, a API de Streams e o pacote `java.util.function`.
Seu objetivo principal é melhorar a legibilidade e a segurança do código, tornando-o mais fácil de manter e menos propenso a erros, especialmente em situações onde a ausência de valor é legítima.

## Quando utilizar o `java.util.Optional`?
O `Optional` é útil nos seguintes casos:

1. Use o `Optional` quando o valor retornado por um método pode ser `null` ou ausente:
   ```java
   Optional<String> nome = buscarNomePorId(id);
   nome.ifPresent(n -> System.out.println("Nome encontrado: " + n));
   ```
   
2. Para definir um valor padrão e evitar NullPointerException:
   ```java
   String nome = nomeOptional.orElse("Nome padrão");
   ```
   
3. Para lidar com a ausência de valor de forma segura e funcional:
    ```java
    Optional<String> nome = Optional.of("Carlos");
    nome.ifPresent(System.out::println);
    ```
    ```java
    Optional<String> nome = Optional.of("Carlos");
    nome.filter(n -> n.startsWith("C"))
    .ifPresent(System.out::println);
    ```

## Quando não utilizar o java.util.Optional?
Evite usar o Optional nos seguintes cenários:
1. Quando a performance é crítica: o uso excessivo de Optional pode ser prejudicial;
2. Quando o valor é sempre esperado;
3. Em campos de entidades ou dados persistidos: não mapear campos com Optional em frameworks como JPA;
4. Não para substituir completamente o uso de null: utilizar quando a ausência de valor é um comportamento legítimo;
5. Não utilizar em parâmetros de métodos: torna a assinatura mais difícil de entender, aumenta a complexidade e pode causar problemas de interoperabilidade com frameworks.

## ⚠️ Resumo das Fricções com `Optional` em Frameworks

Embora `Optional` seja útil para expressar ausência de valor de forma mais segura, seu uso em **parâmetros de métodos**, **campos de DTOs** ou **entidades** pode gerar fricções com frameworks como Spring, Jackson e JPA.

| Framework           | Problemas ao usar `Optional` como parâmetro ou atributo                                                                 |
|---------------------|--------------------------------------------------------------------------------------------------------------------------|
| **Spring MVC**      | O uso de `Optional` em `@RequestParam`, `@PathVariable` ou `@RequestBody` pode dificultar o binding automático e tornar a assinatura do método menos clara. |
| **Jackson**         | A desserialização automática de campos do tipo `Optional<T>` pode falhar, lançar exceções ou retornar valores inesperados. |
| **Bean Validation** | Anotações como `@NotNull`, `@NotBlank` e `@Size` não funcionam diretamente com o conteúdo de um `Optional`.               |
| **JPA / Hibernate** | Campos `Optional` em entidades não são suportados nativamente e devem ser evitados para não comprometer o mapeamento.     |

### ✅ Boas Práticas

- Use `Optional` **apenas como valor de retorno** de métodos de serviço ou repositórios.
- Em **controllers**, receba parâmetros como tipos diretos (`String`, `Integer`, etc.) e utilize `Optional.ofNullable(...)` internamente, se necessário.
- Em **DTOs e entidades**, mantenha os campos com tipos simples e evite `Optional`.
- Para validação, aplique as anotações (`@NotNull`, `@Size`, etc.) diretamente sobre o tipo, sem encapsulá-lo em `Optional`.


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
  
# **Sobre este simples projeto: Exemplos de Uso de `Optional` em Java**
Este repositório contém um exemplo prático de como utilizar a classe `Optional` em Java. 
O objetivo principal é demonstrar boas práticas e técnicas para evitar exceções de ponteiro nulo (`NullPointerException`), reduzir a verbosidade do código e melhorar a legibilidade.

## **Estrutura do Projeto**
O projeto é uma aplicação Spring Boot com Maven, com foco na demonstração do uso de `Optional` em diferentes cenários. Ele contém as seguintes partes principais:

- **Entidades** (`br.com.gavb.entities`): Representações dos dados que serão processados (por exemplo, `AddressInput` e `PersonInput`).
- **Mappers** (`br.com.gavb.mappers`): Classes para mapear objetos de entrada para entidades de domínio.
- **Serviços** (`br.com.gavb.services`): Contêm a lógica de negócios que processa as entradas e aplica as regras de negócio.
- **Dependências Maven**: Gerenciamento das dependências através do arquivo `pom.xml`.

## **Como baixar o Projeto**
1. Clone este repositório para sua máquina local:
    ```bash
    git clone https://github.com/osniantonio/estudos.git
    cd java/javaoptional
    ```