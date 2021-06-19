package com.ken.backend.controller;


import com.ken.backend.entity.Customer;
import com.ken.backend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer){
        return customerService.createNewCustomer(customer);
    }

    @GetMapping("/{page}/{size}")
    public Page<Customer> getAll(@PathVariable("page") int page, @PathVariable("size") int size){
        return customerService.getAll(page,size);
    }

    @PutMapping
    public void updateCustomer(@RequestBody  Customer customer){
        customerService.updateCustomer(customer);
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable("id") Integer Id){
        return customerService.getCustomerById(Id);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomerById(@PathVariable("id") Integer Id){
        customerService.deleteCustomerById(Id);
    }
}
