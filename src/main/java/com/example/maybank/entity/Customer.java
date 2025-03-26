package com.example.maybank.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="Customers")
@Getter
@Setter
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String customerId;
    private String customerName;
    private Long customerAccount;
    private String description;

    @Version
    private Integer version;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(Id, customer.Id) && Objects.equals(customerId, customer.customerId) && Objects.equals(customerName, customer.customerName) && Objects.equals(customerAccount, customer.customerAccount) && Objects.equals(description, customer.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, customerId, customerName, customerAccount, description);
    }
}
