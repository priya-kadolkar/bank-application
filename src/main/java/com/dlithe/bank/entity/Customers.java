package com.dlithe.bank.entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@Table(name = "customers")
public class Customers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    int id;
    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "mobile_number")
    String mobileNumber;

    @Column(name = "email_id")
    String emailId;

    @Column(name = "date_of_birth")
    Date dateOfBirth;

    @Column(name = "address")
    String address;

    @Column(name = "password")
    String password;

    @Column(name = "confirm_password")
    String confirmPassword;

}
