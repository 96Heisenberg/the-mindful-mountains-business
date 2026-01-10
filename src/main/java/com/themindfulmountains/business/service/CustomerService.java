package com.themindfulmountains.business.service;

import com.themindfulmountains.business.model.Customer;
import com.themindfulmountains.business.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(String customerId) {
        return customerRepository.findById(customerId);
    }

    public boolean updateCustomer(String customerId, Customer updatedCustomer) {
        Optional<Customer> existing = customerRepository.findById(customerId);

        if (existing.isPresent()) {
            Customer customer = existing.get();
            customer.setName(updatedCustomer.getName());
            customer.setEmail(updatedCustomer.getEmail());
            customer.setAge(updatedCustomer.getAge());
            customer.setContactNo(updatedCustomer.getContactNo());

            customerRepository.save(customer);
            return true;
        }
        return false;
    }
}
