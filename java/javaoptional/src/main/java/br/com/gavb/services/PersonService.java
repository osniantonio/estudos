package br.com.gavb.services;

import br.com.gavb.entities.PersonInput;
import br.com.gavb.mappers.PersonMapper;

import java.util.Optional;

public class PersonService {

    // Processando a pessoa com Optional e exibindo as melhores práticas
    public void processPerson(PersonInput input) {
        Optional<PersonInput> optionalPerson = Optional.ofNullable(input);

        // Usando .map() para transformar a pessoa em uma string
        String personEntity = optionalPerson
                .map(PersonMapper::toEntity)
                .orElse("Pessoa não disponível");

        // Exibe a transformação ou valor default
        System.out.println("Resultado do processamento da pessoa: " + personEntity);

        // Usando .ifPresentOrElse() para executar ação com ou sem o valor
        optionalPerson.ifPresentOrElse(
                person -> {
                    // Lógica de negócio se a pessoa não for nula
                    System.out.println("Pessoa processada: " + person);
                },
                () -> System.out.println("Pessoa nula recebida. Nada a processar.")
        );

        // Usando .filter() para fazer validações
        boolean isValidPerson = optionalPerson
                .filter(person -> person.getPersonName() != null && !person.getPersonName().isEmpty())
                .isPresent();

        System.out.println("Pessoa válida? " + isValidPerson);

        // Usando .orElseGet() para fornecer valor default de forma preguiçosa
        String personName = optionalPerson
                .map(PersonInput::getPersonName)
                .orElseGet(() -> "Nome não informado");

        System.out.println("Nome da pessoa: " + personName);

        // Usando .flatMap() se precisássemos de um outro Optional dentro do Optional
        Optional<String> optionalPersonName = optionalPerson
                .flatMap(person -> Optional.ofNullable(person.getPersonName()));

        optionalPersonName.ifPresent(name -> System.out.println("Nome da pessoa: " + name));
    }
}
