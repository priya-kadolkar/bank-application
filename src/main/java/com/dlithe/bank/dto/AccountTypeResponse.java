package com.dlithe.bank.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class AccountTypeResponse {

    private String accountType;
    private String accountNumber;

}
