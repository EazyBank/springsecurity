package org.densoft.springsecurity.controller;

import org.densoft.springsecurity.model.Loan;
import org.densoft.springsecurity.service.LoansService;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoansController {

    private final LoansService loansService;

    public LoansController(LoansService loansService) {
        this.loansService = loansService;
    }

    @GetMapping("/myLoans")
    public List<Loan> getLoans(@RequestParam String email) {
        System.out.println("here is email: "+email);
        return loansService.getLoans(email);
    }
}
