package org.densoft.springsecurity.repository;

import org.densoft.springsecurity.model.AccountTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountTransactionRepo extends JpaRepository<AccountTransaction, Integer> {
    List<AccountTransaction> findByCustomerIdOrderByTransactionIdDesc(int customerId);
}
