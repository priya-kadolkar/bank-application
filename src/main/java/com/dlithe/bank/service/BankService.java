package com.dlithe.bank.service;

import com.dlithe.bank.dto.BaseResponse;
import com.dlithe.bank.dto.CustomerRequest;
import com.dlithe.bank.dto.LoginRequest;
import com.dlithe.bank.dto.SendMoneyRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface BankService {
    ResponseEntity<BaseResponse> registerCustomer(CustomerRequest customerRequest);

    ResponseEntity<BaseResponse> loginCustomer(LoginRequest loginRequest);


    ResponseEntity<BaseResponse> fetchAccountDetailsBasedOOnCustomerId(int customerId,String accountType);


    ResponseEntity<BaseResponse> fetchAccounts(int customerId);

    ResponseEntity<BaseResponse> sendMoney(SendMoneyRequest sendMoneyRequest);
}
