package br.com.gavb.mappers;

import br.com.gavb.entities.AddressInput;

import java.util.Optional;

public class AddressMapper {

    public static String toEntity(AddressInput input) {
        return Optional.ofNullable(input)
                .map(address -> {
                    String street = Optional.ofNullable(address.getStreet()).orElse("Rua não informada");
                    String city = Optional.ofNullable(address.getCity()).orElse("Cidade não informada");
                    return "AddressEntity(rua=" + street + ", cidade=" + city + ")";
                })
                .orElse("Endereço inválido");
    }
}
