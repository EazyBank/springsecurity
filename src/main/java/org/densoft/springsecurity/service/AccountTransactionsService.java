package org.densoft.springsecurity.service;

import org.densoft.springsecurity.model.AccountTransaction;
import org.densoft.springsecurity.repository.AccountTransactionRepo;
import org.densoft.springsecurity.repository.CustomerRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountTransactionsService {

    private final AccountTransactionRepo accountTransactionRepo;
    private final CustomerRepo customerRepo;

    public AccountTransactionsService(AccountTransactionRepo accountTransactionRepo, CustomerRepo customerRepo) {
        this.accountTransactionRepo = accountTransactionRepo;
        this.customerRepo = customerRepo;
    }


    public List<AccountTransaction> getAccountTransactions(String email) {

        return customerRepo.findByEmail(email).map(customer -> accountTransactionRepo.findByCustomerIdOrderByTransactionIdDesc(customer.getId()))
                .orElseThrow(() -> new RuntimeException("no customer found by email : " + email));
    }
}
