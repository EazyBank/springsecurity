package org.densoft.springsecurity.controller;

import org.densoft.springsecurity.model.Account;
import org.densoft.springsecurity.service.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    private final AccountService accountService;


    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/myAccount")
    public Account getAccountDetails(@RequestParam int id) {
        return accountService.getAccount(id);
    }
}
