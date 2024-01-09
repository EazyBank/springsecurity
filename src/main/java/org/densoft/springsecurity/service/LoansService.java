package org.densoft.springsecurity.service;

import org.densoft.springsecurity.model.Loan;
import org.densoft.springsecurity.repository.LoanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoansService {

    private final LoanRepository loanRepository;

    public LoansService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public List<Loan> getLoans(int customerId) {
        return loanRepository.findByCustomerIdOrderByStartDtDesc(customerId);
    }
}
