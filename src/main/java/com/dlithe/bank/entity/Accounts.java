package com.dlithe.bank.entity;

import com.fasterxml.jackson.databind.DatabindException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@Table(name = "accounts")
public class Accounts {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "account_number")
    private int accountNumber;

    @Column(name = "balance")
    private double balance;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "customer_id")
    private int customerId;

}
