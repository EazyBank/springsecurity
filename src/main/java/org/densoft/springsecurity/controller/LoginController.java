package org.densoft.springsecurity.controller;

import org.densoft.springsecurity.model.Customer;
import org.densoft.springsecurity.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    private final CustomerService customerService;

    public LoginController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping("/user")
    public Customer getUserDetails() {
        return customerService.getUser();
    }
}
