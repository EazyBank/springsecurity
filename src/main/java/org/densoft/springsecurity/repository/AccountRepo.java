package org.densoft.springsecurity.repository;

import org.densoft.springsecurity.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepo extends JpaRepository<Account, Integer> {
    Optional<Account> findByCustomerId(int customerId);
}
