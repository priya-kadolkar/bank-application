package com.dlithe.bank.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class AccountDetails {
    private int accountNumber;
    private String firstName;
    private String lastName;
    private String accountType;
    private double balance;
    private Date createdDate;
}
