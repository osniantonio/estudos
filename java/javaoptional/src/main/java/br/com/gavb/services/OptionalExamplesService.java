package br.com.gavb.services;

import br.com.gavb.entities.AddressInput;
import br.com.gavb.entities.PersonInput;

import java.util.List;
import java.util.Optional;

public class OptionalExamplesService {
    public static void main(String[] args) {

        AddressInput address = new AddressInput("Rua A", "12345-678", "SP", "São Paulo");
        PersonInput person = new PersonInput("João", List.of(address));

        // Exemplo de map()
        Optional<PersonInput> optionalPerson = Optional.ofNullable(person);

        // .map() para transformar o valor dentro do Optional
        Optional<String> personName = optionalPerson.map(PersonInput::getPersonName);
        System.out.println("Person Name: " + personName.orElse("Pessoa não disponível"));

        // Exemplo de orElse()
        Optional<AddressInput> optionalAddress = Optional.ofNullable(address);
        AddressInput addressResult = optionalAddress.orElse(new AddressInput("Endereço não encontrado", "", "", ""));
        System.out.println("Address Street: " + addressResult.getStreet());

        // Exemplo de ifPresentOrElse()
        optionalAddress.ifPresentOrElse(
                addr -> System.out.println("Endereço processado: " + addr.getStreet()),
                () -> System.out.println("Endereço não disponível.")
        );

        // Exemplo de filter()
        Optional<PersonInput> filteredPerson = optionalPerson.filter(p -> p.getPersonName() != null && !p.getPersonName().isEmpty());
        System.out.println("Pessoa válida? " + filteredPerson.isPresent());

        // Exemplo de orElseGet()
        String name = optionalPerson.map(PersonInput::getPersonName)
                .orElseGet(() -> "Nome não informado");
        System.out.println("Nome: " + name);

        // Exemplo de flatMap()
        Optional<String> addressStreet = optionalAddress.flatMap(a -> Optional.ofNullable(a.getStreet()));
        addressStreet.ifPresent(street -> System.out.println("Rua extraída do endereço: " + street));
    }
}
