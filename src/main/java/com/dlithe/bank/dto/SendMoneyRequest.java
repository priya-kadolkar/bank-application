package com.dlithe.bank.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class SendMoneyRequest {

    private String transactionId;
    private String fromName;
    private String toName;
    private double transactionAmount;
    private String transactionDate;
    private String status;

    private int accountId;
    private String accountType;


}
