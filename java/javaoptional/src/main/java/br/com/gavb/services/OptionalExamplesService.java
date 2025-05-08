package br.com.gavb.services;

import br.com.gavb.entities.AddressInput;
import br.com.gavb.entities.PersonInput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class OptionalExamplesService {
    public static void main(String[] args) {

        // Explicar o uso de of ou ofNullable
        Optional<String> nomeTeste1 = Optional.of("Teste1");
        Optional<String> nomeTeste2 = Optional.ofNullable(" ");
        if (nomeTeste1.isEmpty()) {
            System.out.println("O nomeTeste2 está vazio!");
        }
        if (nomeTeste2.isEmpty()) {
            System.out.println("O nomeTeste2 está vazio!");
        }
        if (nomeTeste2.isPresent() && nomeTeste2.get().isBlank()) {
            System.out.println("O nomeTeste2 está vazio!");
        }

        // Verifica se o valor é "vazio" (considerando espaços em branco como vazio)
        if (nomeTeste2.filter(s -> !s.trim().isEmpty()).isEmpty()) {
            System.out.println("O nomeTeste2 está vazio (apenas espaços ou null)!");
        } else {
            System.out.println("O nomeTeste2 contém: " + nomeTeste2.get());
        }

        Optional<String> nomeTeste3 = Optional.empty();
        if (nomeTeste3.isEmpty()) {
            System.out.println("O Optional nomeTeste3 está vazio!");
        }

        Optional<String> opt1 = Optional.ofNullable("Hello");
        Optional<String> opt2 = Optional.of("Hello");

        // Comparação de identidade
        // NÃO FAÇA ISSO para Optional
        if (opt1 == opt2) {
            System.out.println("Mesma identidade (incorreto)");
        } else {
            System.out.println("Identidades diferentes (correto)");
        }
        // FAÇA ISSO para Optional
        if (opt1.equals(opt2)) {
            System.out.println("Mesmo valor (correto)"); // Resultado correto: Mesmo valor
        }

        // Não é recomendado utilizar comparações com (==) mas aqui no código a seguir são iguais (empty)?
        Optional<String> optional1 = Optional.empty();
        Optional<String> optional2 = Optional.empty();
        if (optional1 == optional2) {
            System.out.println("Identidades iguais");
        } else {
            System.out.println("Identidades diferentes");
        }
        // Utilizar o equals por recomendação na documentação do Optional
        if (optional1.equals(optional2)) {
            System.out.println("Identidades iguais");
        }

        // Sempre devemos verificar se está presente e obter o valor?
        // E para o Optional<String> opt1 = Optional.ofNullable(null); o código abaixo para rodar ou dar uma Exception e qual Exception?
        System.out.println(opt1.get());
        if (opt1.isPresent()) {
            System.out.println(opt1.get());
        }

        AddressInput address = new AddressInput("Rua A", "12345-678", "SP", "São Paulo");
        PersonInput person = new PersonInput("João", List.of(address));
        // PersonInput person = new PersonInput(null, List.of(address));

        // Exemplo com map()
        Optional<PersonInput> optionalPerson = Optional.ofNullable(person);

        // .map() para transformar o valor dentro do Optional
        Optional<String> personName = optionalPerson.map(PersonInput::getPersonName);
        System.out.println("Person Name: " + personName.orElse("Pessoa não disponível"));
        System.out.println("Person Name: " + personName);

        // Exemplo de orElse()
        //Optional<AddressInput> optionalAddress = Optional.ofNullable(address);
        Optional<AddressInput> optionalAddress = Optional.of(address);
        AddressInput addressResult = optionalAddress.orElse(new AddressInput("Endereço não encontrado", "", "", ""));
        System.out.println("Address Street: " + addressResult.getStreet());

        // Exemplo de ifPresentOrElse()
        optionalAddress.ifPresentOrElse(
                addr -> System.out.println("Endereço processado: " + addr.getStreet()),
                () -> System.out.println("Endereço não disponível.")
        );

        // Exemplo de filter()
        String inputSenha = "ABCD";
        Optional<String> senha = Optional.ofNullable(inputSenha);
        String senhaValida = senha
                .filter(s -> !s.isBlank())
                .filter(s -> s.length() >= 3)
                .orElseThrow(() -> new IllegalArgumentException("Senha inválida"));
        System.out.println("Senha válida: " + senhaValida);

        // Assim? qual o resultado
        List<String> senhas = List.of("senha123", "senha1234");
        senhas.stream()
                .map(s -> Optional.ofNullable(s)
                        .filter(str -> !str.isBlank())
                        .filter(str -> str.length() >= 3))
                .map(opt -> opt.orElseThrow(() -> new IllegalArgumentException("Senha inválida")))
                .forEach(item -> System.out.println("Senha válida: " + item));

        // Assim? qual o resultado
        List<String> senhasList = new ArrayList<>(Arrays.asList("senha123", "senha1234"));
        senhasList.stream()
                .map(s -> Optional.ofNullable(s)
                        .filter(str -> !str.isBlank())
                        .filter(str -> str.length() >= 3))
                .map(opt -> opt.orElseThrow(() -> new IllegalArgumentException("Senha inválida")))
                .forEach(item -> System.out.println("Senha válida: " + item));


        // Exemplo de orElseGet()
        Optional<PersonInput> optionalPersonII = Optional.ofNullable(null);
        String name = optionalPersonII.map(PersonInput::getPersonName).orElseGet(() -> "Nome não informado");
        System.out.println("Nome: " + name);

        // Exemplo de flatMap()
        Optional<String> addressStreet = optionalAddress.flatMap(a -> Optional.ofNullable(a.getStreet()));
        addressStreet.ifPresent(street -> System.out.println("Rua extraída do endereço: " + street));
    }
}
