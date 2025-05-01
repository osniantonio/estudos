package br.com.gavb.mappers;

import br.com.gavb.entities.PersonInput;

import java.util.Optional;

public class PersonMapper {

    public static String toEntity(PersonInput input) {
        return Optional.ofNullable(input)
                .map(person -> {
                    String personName = Optional.ofNullable(person.getPersonName()).orElse("Nome não informado");
                    int addressCount = Optional.ofNullable(person.getAddresses())
                            .map(addresses -> addresses.size())
                            .orElse(0);
                    return "PersonEntity(nome=" + personName + ", totalEnderecos=" + addressCount + ")";
                })
                .orElse("Pessoa inválida");
    }
}
