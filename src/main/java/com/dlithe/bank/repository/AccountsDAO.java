package com.dlithe.bank.repository;

import com.dlithe.bank.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountsDAO extends JpaRepository<Accounts,Integer> {

    @Query(value = "SELECT * FROM accounts WHERE customer_id=?1",nativeQuery = true)
    Accounts findByCustomerId(int customerId);

    @Query(value = "SELECT * FROM accounts WHERE customer_id=?1 AND account_type=?2",nativeQuery = true)
    Accounts findByCustomerIdAndAccountType(int customerId, String accountType);

    @Query(value = "SELECT * FROM accounts WHERE customer_id=?1",nativeQuery = true)
    List<Accounts> findAccountsByCustomerId(int customerId);

    @Query(value = "select * from accounts where id=?1 and account_type=?2",nativeQuery = true)
    Accounts findByAccountIdAndAccountType(int accountId, String accountType);

}
