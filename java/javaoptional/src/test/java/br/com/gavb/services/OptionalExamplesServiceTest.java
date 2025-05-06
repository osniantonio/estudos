package br.com.gavb.services;

import br.com.gavb.entities.AddressInput;
import br.com.gavb.entities.PersonInput;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OptionalExamplesServiceTest {

    @Test
    void testMap() {
        // Cria exemplo de AddressInput e PersonInput
        AddressInput address = new AddressInput("Rua A", "12345-678", "SP", "São Paulo");
        PersonInput person = new PersonInput("João", List.of(address));

        // Optional com PersonInput
        Optional<PersonInput> optionalPerson = Optional.ofNullable(person);

        // Usando map para obter o nome da pessoa
        Optional<String> personName = optionalPerson.map(PersonInput::getPersonName);

        // Verificando o nome da pessoa
        assertEquals("João", personName.orElse("Pessoa não disponível"));
    }

    @Test
    void testOrElse() {
        // Criando AddressInput
        AddressInput address = new AddressInput("Rua A", "12345-678", "SP", "São Paulo");

        // Optional com AddressInput
        Optional<AddressInput> optionalAddress = Optional.ofNullable(address);

        // Usando orElse para fornecer um valor default se o Optional estiver vazio
        AddressInput addressResult = optionalAddress.orElse(new AddressInput("Endereço não encontrado", "", "", ""));

        // Verificando o valor do endereço
        assertEquals("Rua A", addressResult.getStreet());
    }

    @Test
    void testIfPresentOrElse() {
        // Criando AddressInput
        AddressInput address = new AddressInput("Rua A", "12345-678", "SP", "São Paulo");

        // Optional com AddressInput
        Optional<AddressInput> optionalAddress = Optional.ofNullable(address);

        // Verificando se o valor está presente ou não
        String result = optionalAddress
                .map(addr -> "Endereço processado: " + addr.getStreet())
                .orElse("Endereço não disponível.");

        // Esperamos que o resultado seja o endereço processado
        assertEquals("Endereço processado: Rua A", result);
    }

    @Test
    void testFilter() {
        // Criando AddressInput e PersonInput
        AddressInput address = new AddressInput("Rua A", "12345-678", "SP", "São Paulo");
        PersonInput person = new PersonInput("João", List.of(address));

        // Optional com PersonInput
        Optional<PersonInput> optionalPerson = Optional.ofNullable(person);

        // Usando filter para verificar se a pessoa tem um nome válido
        Optional<PersonInput> filteredPerson = optionalPerson.filter(p -> p.getPersonName() != null && !p.getPersonName().isEmpty());

        // Verificando se a pessoa é válida
        assertTrue(filteredPerson.isPresent());
    }

    @Test
    void testOrElseGet() {
        // Criando AddressInput
        AddressInput address = new AddressInput("Rua A", "12345-678", "SP", "São Paulo");

        // Optional com PersonInput
        Optional<PersonInput> optionalPerson = Optional.ofNullable(new PersonInput("João", List.of(address)));

        // Usando orElseGet para fornecer um valor default de forma preguiçosa
        String name = optionalPerson.map(PersonInput::getPersonName)
                .orElseGet(() -> "Nome não informado");

        // Verificando o nome
        assertEquals("João", name);
    }

    @Test
    void testFlatMap() {
        // Criando AddressInput
        AddressInput address = new AddressInput("Rua A", "12345-678", "SP", "São Paulo");

        // Optional com AddressInput
        Optional<AddressInput> optionalAddress = Optional.ofNullable(address);

        // Usando flatMap para pegar a rua do endereço, se presente
        Optional<String> addressStreet = optionalAddress.flatMap(a -> Optional.ofNullable(a.getStreet()));

        // Verificando se a rua foi extraída corretamente
        assertTrue(addressStreet.isPresent());
        assertEquals("Rua A", addressStreet.get());
    }
}