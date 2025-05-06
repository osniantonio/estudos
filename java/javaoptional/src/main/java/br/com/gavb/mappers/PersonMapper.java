package br.com.gavb.mappers;

import br.com.gavb.business.PersonBusiness;
import br.com.gavb.entities.PersonInput;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PersonMapper {

    public static PersonBusiness convert(PersonInput input) {

        PersonBusiness personBusiness = new PersonBusiness();

        Optional.ofNullable(input)
                .map(PersonInput::getPersonName)
                .ifPresent(personBusiness::setPersonName);

        // Aqui ocorre um NullPointerException
//        Optional.ofNullable(input)
//                .map(personalInput -> personalInput.getAddresses()
//                        .stream()
//                        .map(AddressMapper::convertAddress)
//                        .collect(Collectors.toList()))
//                .ifPresent(personBusiness::setAddresses);

        // Usando Optional para mapear a lista de endereços, tratando caso seja nula
        Optional.ofNullable(input)
                .map(PersonInput::getAddresses)
                .filter(addresses -> !addresses.isEmpty())
                .map(addresses -> addresses.stream()
                        .map(AddressMapper::convertAddress)
                        .collect(Collectors.toList()))
                .ifPresent(personBusiness::setAddresses);

        return personBusiness;
    }
}


