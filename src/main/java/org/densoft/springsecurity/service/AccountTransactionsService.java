package org.densoft.springsecurity.service;

import org.densoft.springsecurity.model.AccountTransaction;
import org.densoft.springsecurity.repository.AccountTransactionRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountTransactionsService {

    private final AccountTransactionRepo accountTransactionRepo;

    public AccountTransactionsService(AccountTransactionRepo accountTransactionRepo) {
        this.accountTransactionRepo = accountTransactionRepo;
    }


    public List<AccountTransaction> getAccountTransactions(int customerId){
        return accountTransactionRepo.findByCustomerIdOrderByTransactionIdDesc(customerId);
    }
}
