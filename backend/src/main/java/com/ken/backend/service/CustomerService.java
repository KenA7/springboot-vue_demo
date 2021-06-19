package com.ken.backend.service;

import com.ken.backend.entity.Customer;
import org.springframework.data.domain.Page;


public interface CustomerService {

    Customer createNewCustomer(Customer newCustomer);

    Page<Customer> getAll(int page, int size);

    void updateCustomer(Customer customer);

    Customer getCustomerById(Integer Id);

    void deleteCustomerById(Integer Id);

}
