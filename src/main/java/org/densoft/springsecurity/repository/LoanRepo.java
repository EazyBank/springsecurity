package org.densoft.springsecurity.repository;

import org.densoft.springsecurity.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepo extends JpaRepository<Loan, Integer> {

//    @PreAuthorize("hasRole('USER')")
    List<Loan> findByCustomerIdOrderByStartDtDesc(int customerId);
}