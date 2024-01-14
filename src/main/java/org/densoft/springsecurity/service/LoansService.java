package org.densoft.springsecurity.service;

import org.densoft.springsecurity.model.Loan;
import org.densoft.springsecurity.repository.CustomerRepo;
import org.densoft.springsecurity.repository.LoanRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoansService {

    private final LoanRepo loanRepo;
    private final CustomerRepo customerRepo;

    public LoansService(LoanRepo loanRepo, CustomerRepo customerRepo) {
        this.loanRepo = loanRepo;
        this.customerRepo = customerRepo;
    }

    public List<Loan> getLoans(String email) {
        return customerRepo.findByEmail(email)
                .map(customer -> loanRepo.findByCustomerIdOrderByStartDtDesc(customer.getId()))
                .orElseThrow(() -> new RuntimeException("no customer found with the email:  " + email));
    }
}
