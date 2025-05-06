package br.com.gavb.business;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddressBusiness {
    private String street;
    private String zipCode;
    private String state;
    private String city;
}
