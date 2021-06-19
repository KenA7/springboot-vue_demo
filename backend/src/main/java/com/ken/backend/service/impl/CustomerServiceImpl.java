package com.ken.backend.service.impl;

import com.ken.backend.entity.Customer;
import com.ken.backend.repository.CustomerRepository;
import com.ken.backend.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;



@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer createNewCustomer(Customer newCustomer) {
        newCustomer.setCreateTime(LocalDateTime.now());
        return customerRepository.save(newCustomer);
    }

    @Override
    public Page<Customer> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page-1,size);
        return customerRepository.findAll(pageable);
    }

    @Override
    public void updateCustomer(Customer customer) {

            Customer currentCustomer = customerRepository.getById(customer.getId());
            currentCustomer.setEmail(customer.getEmail());
            currentCustomer.setName(customer.getName());
            currentCustomer.setCreateTime(customer.getCreateTime());
            customerRepository.save(customer);

        }

    @Override
    public Customer getCustomerById(Integer Id) {
        return customerRepository.findById(Id).get();
    }

    @Override
    public void deleteCustomerById(Integer Id) {
        customerRepository.deleteById(Id);
    }
}



