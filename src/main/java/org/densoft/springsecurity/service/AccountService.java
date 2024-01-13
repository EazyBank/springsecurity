package org.densoft.springsecurity.service;

import org.densoft.springsecurity.model.Account;
import org.densoft.springsecurity.repository.AccountRepo;
import org.densoft.springsecurity.repository.CustomerRepo;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepo accountRepo;
    private final CustomerRepo customerRepo;

    public AccountService(AccountRepo accountRepo, CustomerRepo customerRepo) {
        this.accountRepo = accountRepo;
        this.customerRepo = customerRepo;
    }

    public Account getAccount(String email) {

        return customerRepo.findByEmail(email)
                .map(customer -> accountRepo.findByCustomerId(customer.getId()).get()).
                orElseThrow(() -> new RuntimeException("No account found for customer id: %s".formatted(email)));

    }
}
