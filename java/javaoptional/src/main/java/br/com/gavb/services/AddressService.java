package br.com.gavb.services;

import br.com.gavb.entities.AddressInput;
import br.com.gavb.mappers.AddressMapper;

import java.util.Optional;

public class AddressService {

    // Processando o endereço com Optional e exibindo as melhores práticas
    public void processAddress(AddressInput input) {
        Optional<AddressInput> optionalAddress = Optional.ofNullable(input);

        // Usando .map() para transformar o endereço em uma string
        String addressEntity = optionalAddress
                .map(AddressMapper::toEntity)
                .orElse("Endereço não disponível");

        // Exibe a transformação ou valor default
        System.out.println("Resultado do processamento do endereço: " + addressEntity);

        // Usando .ifPresentOrElse() para executar ação com ou sem o valor
        optionalAddress.ifPresentOrElse(
                address -> {
                    // Lógica de negócio se o endereço não for nulo
                    System.out.println("Endereço processado: " + address);
                },
                () -> System.out.println("Endereço nulo recebido. Nada a processar.")
        );

        // Usando .filter() para fazer validações
        boolean isValidAddress = optionalAddress
                .filter(address -> address.getStreet() != null && !address.getStreet().isEmpty())
                .isPresent();

        System.out.println("Endereço válido? " + isValidAddress);

        // Usando .orElseGet() para fornecer valor default de forma preguiçosa
        String city = optionalAddress
                .map(AddressInput::getCity)
                .orElseGet(() -> "Cidade não informada");

        System.out.println("Cidade do endereço: " + city);

        // Usando .flatMap() se precisássemos de um outro Optional dentro do Optional
        Optional<String> optionalStreet = optionalAddress
                .flatMap(address -> Optional.ofNullable(address.getStreet()));

        optionalStreet.ifPresent(street -> System.out.println("Rua do endereço: " + street));
    }
}
