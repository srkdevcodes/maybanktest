package com.example.maybank.service.impl;

import com.example.maybank.dto.CustomerRequest;
import com.example.maybank.entity.Customer;
import com.example.maybank.repository.CustomerRepository;
import com.example.maybank.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Page<Customer> getCustomerList(PageRequest pageRequest) {
        return customerRepository.findAll(pageRequest);
    }

    @Override
    @Transactional
    public Customer updateCustomer(CustomerRequest customerRequest) {
        Customer customer = customerRepository.findById(customerRequest.id())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        customer.setDescription(customerRequest.description());

        return customerRepository.save(customer);
    }

    @Override
    public Page<Customer> findCustomer(String customerId, Long customerAccount, String description, PageRequest pageRequest) {
        return customerRepository.find(customerId,customerAccount,description, pageRequest);
    }
}
