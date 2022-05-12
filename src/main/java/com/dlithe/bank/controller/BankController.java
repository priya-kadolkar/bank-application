package com.dlithe.bank.controller;

import com.dlithe.bank.dto.BaseResponse;
import com.dlithe.bank.dto.CustomerRequest;
import com.dlithe.bank.dto.LoginRequest;
import com.dlithe.bank.dto.SendMoneyRequest;
import com.dlithe.bank.service.BankService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@CrossOrigin(origins = "*")
public class BankController {

    @Autowired
    private BankService bankService;

    //this endpoint register a new customer
    @PostMapping("register-customer")
    public ResponseEntity<BaseResponse> registerNewCustomer(@RequestBody CustomerRequest customerRequest) {
        log.info("Inside bank controller method registerNewCustomer  with request : {} ", customerRequest);
        return bankService.registerCustomer(customerRequest);
    }

    @PostMapping("customer-login")
    public ResponseEntity<BaseResponse> loginCustomer(@RequestBody LoginRequest loginRequest) {
        if (loginRequest == null) {
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setMessage("Login details can not be null ");
            baseResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
            baseResponse.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);

        }

        return bankService.loginCustomer(loginRequest);

    }

    //Fetching list of account based on customer id
    @GetMapping("fetch-list-of-account/{customerId}")
    public ResponseEntity<BaseResponse> getAccounts(@PathVariable int customerId) {
        if ((customerId == 0) || String.valueOf(customerId) == null) {
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setMessage("Values cannot be 0 or null");
            baseResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
            baseResponse.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
        }
        return bankService.fetchAccounts(customerId);
    }

    //fetching account details with customer id and account type
    @GetMapping("fetch-account-details/{customerId}/{accountType}")
    public ResponseEntity<BaseResponse> getAccountDetailsBasedOOnCustomerId(@PathVariable int customerId, @PathVariable String accountType) {
        if ((customerId == 0) || String.valueOf(customerId) == null || String.valueOf(accountType) == null) {
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setMessage("Values cannot be 0 or null");
            baseResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
            baseResponse.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
        }
        return bankService.fetchAccountDetailsBasedOOnCustomerId(customerId, accountType);
    }

    @PostMapping("send-money")
    public ResponseEntity<BaseResponse> sendMoney(@RequestBody SendMoneyRequest sendMoneyRequest){
        BaseResponse baseResponse=new BaseResponse();
        if(sendMoneyRequest==null){
            baseResponse.setMessage("Value cannot be null");
            baseResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
            baseResponse.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());

            return new ResponseEntity<>(baseResponse,HttpStatus.BAD_REQUEST);
        }
        return bankService.sendMoney(sendMoneyRequest);
    }


}
