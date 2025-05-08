# Overview e Guia de Uso — java.util.Optional
O `java.util.Optional` foi introduzido no Java desde a versão 1.8 como parte de um conjunto de novas funcionalidades, incluindo expressões lambda, a API de Streams e o pacote `java.util.function`.
Seu objetivo principal é melhorar a legibilidade e a segurança do código, tornando-o mais fácil de manter e menos propenso a erros, especialmente em situações onde a ausência de valor é legítima.

Obs.: Classe baseada em valor e o uso de operações sensíveis à identidade (incluindo igualdade de referência (==), 
código hash de identidade ou sincronização) em instâncias de `Optional` deve ser evitado.

Fonte: https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html

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
Evite usar o Optional nos seguintes cenários (Conforme recomendações da JSR-335):
1. Quando a performance é crítica: o uso excessivo de Optional pode ser prejudicial;
2. Quando o valor é sempre esperado;
3. Em campos de entidades ou dados persistidos: não mapear campos com Optional em frameworks como JPA;
4. Não para substituir completamente o uso de null: utilizar quando a ausência de valor é um comportamento legítimo;
5. Não utilizar em parâmetros de métodos: torna a assinatura mais difícil de entender, aumenta a complexidade e pode causar problemas de interoperabilidade com frameworks.

Obs.: A JSR-335 é a Java Specification Request que introduziu expressões lambda, referências a métodos, interfaces funcionais e melhorias nas APIs da biblioteca padrão no Java SE 8. Ela é oficialmente intitulada: JSR 335: Lambda Expressions for the Java Programming Language.

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

## Diferença entre `Optional.of()` e `Optional.ofNullable()` em Java
A principal diferença entre `Optional.of()` e `Optional.ofNullable()` em Java está no tratamento de valores nulos.

### `Optional.of(T value)`
Esse método é usado quando você tem certeza de que o valor que está sendo passado **não é `null`**. Se o valor for `null`, ele lançará uma exceção `NullPointerException`.
Exemplo:
```java
Optional<String> optional = Optional.of("Hello");  // Correto
Optional<String> optionalNull = Optional.of(null); // Lança NullPointerException
```

### Optional.ofNullable(T value)
Esse método pode ser usado tanto para valores não nulos quanto nulos. Se o valor for null, o Optional retornará um Optional.empty(), que é uma instância de Optional que não contém valor.
Exemplo:
```java
Optional<String> optional = Optional.ofNullable("Hello"); // Correto
Optional<String> optionalNull = Optional.ofNullable(null); // Retorna Optional.empty
```

### Resumo
Optional.of() não aceita null e lança NullPointerException se o valor for null.
Optional.ofNullable() aceita null e, se o valor for null, retorna Optional.empty().

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