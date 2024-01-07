package org.densoft.springsecurity.service;

import org.densoft.springsecurity.model.Customer;
import org.densoft.springsecurity.repository.CustomerRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    public CustomerService(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveUser(Customer newCustomer) {
        customerRepository.findByEmail(newCustomer.getEmail()).ifPresent(customer -> {
            throw new RuntimeException("User with matching details found");
        });


        newCustomer.setPassword(passwordEncoder.encode(newCustomer.getPassword()));

        customerRepository.save(newCustomer);
    }
}
