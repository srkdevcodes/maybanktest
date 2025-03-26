package com.example.maybank.config;

import com.example.maybank.entity.Customer;
import org.springframework.batch.item.ItemProcessor;

public class CustomerProcessor implements ItemProcessor<Customer, Customer> {
    @Override
    public Customer process(Customer customer) throws Exception {
        customer.setCustomerName(customer.getCustomerName().toUpperCase());
        return customer;
    }
}
