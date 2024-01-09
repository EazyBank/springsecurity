package org.densoft.springsecurity.service;

import org.densoft.springsecurity.model.Account;
import org.densoft.springsecurity.repository.AccountRepo;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepo accountRepo;

    public AccountService(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    public Account getAccount(int customerId) {
        return accountRepo.findByCustomerId(customerId).orElseThrow(() -> new RuntimeException("No account found for customer id: %d".formatted(customerId)));
    }
}
