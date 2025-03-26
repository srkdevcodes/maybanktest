package com.example.maybank.dto;

public record CustomerRequest(long id, String customerId, String customerName,Long customerAccount, String description) {
}
