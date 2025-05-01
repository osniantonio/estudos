package br.com.gavb.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddressInput {
    private String street;
    private String zipCode;
    private String state;
    private String city;
}
