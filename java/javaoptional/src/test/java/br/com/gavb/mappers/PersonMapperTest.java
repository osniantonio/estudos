package br.com.gavb.mappers;

import br.com.gavb.business.PersonBusiness;
import br.com.gavb.entities.AddressInput;
import br.com.gavb.entities.PersonInput;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonMapperTest {

    @Test
    void testConvertPersonWithAddresses() {
        // Criando um AddressInput com valores
        AddressInput address = new AddressInput("Rua A", "12345-678", "SP", "São Paulo");

        // Criando um PersonInput com uma lista de AddressInput
        PersonInput personInput = new PersonInput("João", List.of(address));

        // Convertendo PersonInput para PersonBusiness
        PersonBusiness personBusiness = PersonMapper.convert(personInput);

        // Verificando se a conversão foi bem-sucedida
        assertNotNull(personBusiness);
        assertEquals("João", personBusiness.getPersonName());
        assertNotNull(personBusiness.getAddresses());
        assertEquals(1, personBusiness.getAddresses().size());
        assertEquals("Rua A", personBusiness.getAddresses().get(0).getStreet());
    }

    @Test
    void testConvertPersonWithoutAddresses() {
        // Criando um PersonInput sem endereços
        PersonInput personInput = new PersonInput("Maria", null);

        // Convertendo PersonInput para PersonBusiness
        PersonBusiness personBusiness = PersonMapper.convert(personInput);

        // Verificando se a conversão foi bem-sucedida
        assertNotNull(personBusiness);
        assertEquals("Maria", personBusiness.getPersonName());
        assertNull(personBusiness.getAddresses());
    }

    @Test
    void testConvertPersonWithNullValues() {
        // Criando um PersonInput com valores nulos
        PersonInput personInput = new PersonInput(null, null);

        // Convertendo PersonInput para PersonBusiness
        PersonBusiness personBusiness = PersonMapper.convert(personInput);

        // Verificando se a conversão foi bem-sucedida, mesmo com valores nulos
        assertNotNull(personBusiness);
        assertNull(personBusiness.getPersonName());
        assertNull(personBusiness.getAddresses());
    }

}