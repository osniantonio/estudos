package br.com.gavb.business;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PersonBusiness {
    private String personName;
    private List<AddressBusiness> addresses;
}
