package org.densoft.springsecurity.service;

import org.densoft.springsecurity.model.Customer;
import org.densoft.springsecurity.repository.CustomerRepo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepo customerRepo;
    public CustomerService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }


    public Customer getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        return customerRepo.findByEmail(username).orElseThrow(() -> new RuntimeException("no user found"));
    }
}
