package com.dlithe.bank.serviceimpl;

import com.dlithe.bank.dto.*;
import com.dlithe.bank.entity.Accounts;
import com.dlithe.bank.entity.Transaction;
import com.dlithe.bank.repository.AccountsDAO;
import com.dlithe.bank.repository.TransactionDAO;
import com.dlithe.bank.service.BankService;
import jdk.management.resource.internal.TotalResourceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.dlithe.bank.entity.Customers;
import com.dlithe.bank.repository.CustomersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BankServiceImple implements BankService {

    private int a;

    @Autowired
    private CustomersDAO customersDAO;

    @Autowired
    private AccountsDAO accountsDAO;

    @Autowired
    private TransactionDAO transactionDAO;

    @Override
    public ResponseEntity<BaseResponse> registerCustomer(CustomerRequest customerRequest) {
        log.info("Inside Bank service impl method registerCustomer",customerRequest);
        BaseResponse baseResponse=new BaseResponse();

        Customers customers=new Customers();

        customers.setFirstName(customerRequest.getFirstName());
        customers.setLastName(customerRequest.getLastName());
        customers.setMobileNumber(customerRequest.getMobileNumber());
        customers.setEmailId(customerRequest.getEmailId());
        customers.setDateOfBirth(customerRequest.getDateOfBirth());
        customers.setAddress(customerRequest.getAddress());
        customers.setPassword(customerRequest.getPassword());
        customers.setConfirmPassword(customerRequest.getConfirmPassword());

        baseResponse.setMessage("User data saved successfully..");
        baseResponse.setHttpStatus(HttpStatus.OK);
        baseResponse.setHttpStatusCode(HttpStatus.OK.value());
//        baseResponse.setResponse(customers);

        customersDAO.save(customers);
        log.info("Customer data saved successfully");
        return new ResponseEntity<BaseResponse>(baseResponse,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BaseResponse> loginCustomer(LoginRequest loginRequest) {
        Optional<Customers> customers = Optional.ofNullable(customersDAO.fetchEmailIdAndPassword(loginRequest.getEmailId(), loginRequest.getPassword()));

        BaseResponse baseResponse=new BaseResponse();

        if (!customers.isPresent()) {

            baseResponse.setMessage("Customer is not found ");
            baseResponse.setHttpStatus(HttpStatus.NOT_FOUND);
            baseResponse.setHttpStatusCode(HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(baseResponse, HttpStatus.NOT_FOUND);
        }

        CustomerDetails customerDetails=new CustomerDetails();
        customerDetails.setCustomerId(customers.get().getId());
//        customerDetails.setFirstName(customers.get().getFirstName());

        baseResponse.setMessage("Logged in successfully");
        baseResponse.setHttpStatus(HttpStatus.OK);
        baseResponse.setHttpStatusCode(HttpStatus.OK.value());
        baseResponse.setResponse(customerDetails);
        return  new ResponseEntity<>(baseResponse,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BaseResponse> fetchAccountDetailsBasedOOnCustomerId(int customerId,String accountType) {
        BaseResponse baseResponse=new BaseResponse();

        Optional<Customers> customers=customersDAO.findById(customerId);
        Customers customerFromDatabase=customers.get();

        Accounts accounts=accountsDAO.findByCustomerIdAndAccountType(customerId,accountType);

        AccountDetails accountDetails=new AccountDetails();

        accountDetails.setAccountType(accounts.getAccountType());
        accountDetails.setAccountNumber(accounts.getAccountNumber());
        accountDetails.setBalance(accounts.getBalance());
        accountDetails.setCreatedDate(accounts.getCreatedDate());
        accountDetails.setFirstName(customerFromDatabase.getFirstName());
        accountDetails.setLastName(customerFromDatabase.getLastName());


        baseResponse.setMessage("Account details is fetched successfully");
        baseResponse.setHttpStatus(HttpStatus.OK);
        baseResponse.setHttpStatusCode(HttpStatus.OK.value());
        baseResponse.setResponse(accountDetails);

        return new ResponseEntity<>(baseResponse,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BaseResponse> fetchAccounts(int customerId) {
        log.info("inside fetchAccounts {}: ", customerId);
        BaseResponse baseResponse=new BaseResponse();

        Optional<Customers> customers=customersDAO.findById(customerId);
        Customers customerFromDatabase=customers.get();

        List<Accounts> accountsList=accountsDAO.findAccountsByCustomerId(customerId);

        AccountsResponse accountsResponse=new AccountsResponse();

        List<AccountTypeResponse> accountTypeResponseList=new ArrayList<>();
        for(Accounts account:accountsList){
            AccountTypeResponse accountTypeResponse=new AccountTypeResponse();
            accountTypeResponse.setAccountNumber(account.getAccountNumber());
            accountTypeResponse.setAccountType(account.getAccountType());

            accountTypeResponseList.add(accountTypeResponse);
        }

        accountsResponse.setFirstName(customerFromDatabase.getFirstName());
        accountsResponse.setLastName(customerFromDatabase.getLastName());
        accountsResponse.setAccountType(accountTypeResponseList);

        baseResponse.setMessage("Account fetched successfully..");
        baseResponse.setHttpStatus(HttpStatus.OK);
        baseResponse.setHttpStatusCode(HttpStatus.OK.value());
        baseResponse.setResponse(accountsResponse);

        return new ResponseEntity<>(baseResponse,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BaseResponse> sendMoney(SendMoneyRequest sendMoneyRequest) {
        log.info("Inside Bank service impl method sendMoney {}",sendMoneyRequest);

        double updatedBalance=0.0;
        BaseResponse baseResponse=new BaseResponse();

        Accounts accounts=accountsDAO.findByAccountIdAndAccountType(sendMoneyRequest.getAccountId(),sendMoneyRequest.getAccountType());

        if(accounts.getBalance() < sendMoneyRequest.getTransactionAmount()){
            baseResponse.setMessage("Insufficient balance");
            baseResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
            baseResponse.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());

            return new ResponseEntity<>(baseResponse,HttpStatus.BAD_REQUEST);
        }


        Transaction transaction=new Transaction();

        transaction.setTransactionId(sendMoneyRequest.getTransactionId());
        transaction.setFromName(sendMoneyRequest.getFromName());
        transaction.setToName(sendMoneyRequest.getToName());
        transaction.setTransactionAmount(sendMoneyRequest.getTransactionAmount());
        transaction.setTransactionDate(sendMoneyRequest.getTransactionDate());
        transaction.setStatus(sendMoneyRequest.getStatus());

        transactionDAO.save(transaction);

        updatedBalance=accounts.getBalance()-sendMoneyRequest.getTransactionAmount();
        accounts.setBalance(updatedBalance);

        accountsDAO.save(accounts);

            baseResponse.setMessage("Transaction successful");
            baseResponse.setHttpStatus(HttpStatus.OK);
            baseResponse.setHttpStatusCode(HttpStatus.OK.value());

            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
