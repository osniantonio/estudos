package br.com.gavb.mappers;

import br.com.gavb.business.AddressBusiness;
import br.com.gavb.entities.AddressInput;

import java.util.Optional;

public class AddressMapper {

    public static AddressBusiness convertAddress(AddressInput addressInput) {
        AddressBusiness addressBusiness = new AddressBusiness();

        Optional.ofNullable(addressInput)
                .map(AddressInput::getCity)
                .ifPresent(addressBusiness::setCity);

        Optional.ofNullable(addressInput)
                .map(AddressInput::getState)
                .ifPresent(addressBusiness::setState);

        Optional.ofNullable(addressInput)
                .map(AddressInput::getZipCode)
                .ifPresent(addressBusiness::setZipCode);

        Optional.ofNullable(addressInput)
                .map(AddressInput::getStreet)
                .ifPresent(addressBusiness::setStreet);

        return addressBusiness;
    }
}