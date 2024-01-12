package org.densoft.springsecurity.repository;

import org.densoft.springsecurity.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Integer> {

//    @PreAuthorize("hasRole('USER')")
    List<Loan> findByCustomerIdOrderByStartDtDesc(int customerId);
}