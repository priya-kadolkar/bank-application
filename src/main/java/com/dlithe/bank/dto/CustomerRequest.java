package com.dlithe.bank.dto;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import javax.persistence.Entity;
import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CustomerRequest {
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String emailId;
    private Date dateOfBirth;
    private String address;
    private String password;
    private String confirmPassword;
}
