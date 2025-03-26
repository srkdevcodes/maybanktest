package com.example.maybank.repository;

import com.example.maybank.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    void findByCustomerId(String customerId);

    @Query("select s from Customer s "
            + "where (s.customerId is null or s.customerId = :customerId) "
            +"or (s.customerAccount is null or s.customerAccount = :customerName) "
            +"or (s.description is null or s.description = :description) ")
    Page<Customer> find(String customerId, Long customerName, String description, Pageable pageable);
}
