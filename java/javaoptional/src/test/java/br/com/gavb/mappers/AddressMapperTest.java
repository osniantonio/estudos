package br.com.gavb.mappers;

import br.com.gavb.business.AddressBusiness;
import br.com.gavb.entities.AddressInput;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressMapperTest {
    @Test
    void testConvertAddress() {
        // Criando um objeto AddressInput com valores
        AddressInput addressInput = new AddressInput("Rua A", "12345-678", "SP", "São Paulo");

        // Convertendo AddressInput para AddressBusiness
        AddressBusiness addressBusiness = AddressMapper.convertAddress(addressInput);

        // Verificando se a conversão foi bem-sucedida
        assertNotNull(addressBusiness);
        assertEquals("Rua A", addressBusiness.getStreet());
        assertEquals("12345-678", addressBusiness.getZipCode());
        assertEquals("SP", addressBusiness.getState());
        assertEquals("São Paulo", addressBusiness.getCity());
    }

    @Test
    void testConvertAddressWithNullValues() {
        // Criando um AddressInput com valores nulos
        AddressInput addressInput = new AddressInput(null, null, null, null);

        // Convertendo AddressInput para AddressBusiness
        AddressBusiness addressBusiness = AddressMapper.convertAddress(addressInput);

        // Verificando se os valores nulos foram tratados corretamente
        assertNotNull(addressBusiness);
        assertNull(addressBusiness.getStreet());
        assertNull(addressBusiness.getZipCode());
        assertNull(addressBusiness.getState());
        assertNull(addressBusiness.getCity());
    }

    @Test
    void testConvertAddressWithPartialValues() {
        // Criando um AddressInput com alguns valores nulos
        AddressInput addressInput = new AddressInput("Rua B", null, "MG", null);

        // Convertendo AddressInput para AddressBusiness
        AddressBusiness addressBusiness = AddressMapper.convertAddress(addressInput);

        // Verificando se apenas os campos não nulos foram mapeados corretamente
        assertNotNull(addressBusiness);
        assertEquals("Rua B", addressBusiness.getStreet());
        assertNull(addressBusiness.getZipCode());
        assertEquals("MG", addressBusiness.getState());
        assertNull(addressBusiness.getCity());
    }
}