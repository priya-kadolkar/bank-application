package com.dlithe.bank.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
@EqualsAndHashCode
public class AccountsResponse {

    private String firstName;
    private String lastName;
    private List<AccountTypeResponse> accountType;

}
