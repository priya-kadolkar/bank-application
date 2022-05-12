package com.dlithe.bank.repository;

import com.dlithe.bank.entity.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface CustomersDAO extends JpaRepository<Customers,Integer> {

    @Query(value = "SELECT * from customers WHERE email_id =?1 and password=?2 ", nativeQuery = true)
    Customers fetchEmailIdAndPassword(String emailId, String password);
}
