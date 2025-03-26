package com.example.maybank.service;

import com.example.maybank.dto.CustomerRequest;
import com.example.maybank.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface CustomerService {

    Page<Customer> getCustomerList(PageRequest pageRequest);

    Customer updateCustomer(CustomerRequest customer);

    Page<Customer> findCustomer(String customerId,Long customerAccount,String description, PageRequest pageRequest);
}
