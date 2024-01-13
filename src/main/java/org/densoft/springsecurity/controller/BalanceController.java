package org.densoft.springsecurity.controller;

import org.densoft.springsecurity.model.AccountTransaction;
import org.densoft.springsecurity.service.AccountTransactionsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BalanceController {

    private final AccountTransactionsService accountTransactionsService;

    public BalanceController(AccountTransactionsService accountTransactionsService) {
        this.accountTransactionsService = accountTransactionsService;
    }

    @GetMapping("/myBalance")
    public List<AccountTransaction> getBalanceDetails(@RequestParam String email) {
        return accountTransactionsService.getAccountTransactions(email);
    }
}
