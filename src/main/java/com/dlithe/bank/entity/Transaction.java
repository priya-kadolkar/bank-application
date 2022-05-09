package com.dlithe.bank.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "transaction_id")
    private String transactionId;

    @Column(name = "from_name")
    private String fromName;

    @Column(name = "to_name")
    private String toName;

    @Column(name = "transaction_amount")
    private double transactionAmount;

    @Column(name = "transaction_date")
    private String transactionDate;

    @Column(name = "status")
    private String status;


}
