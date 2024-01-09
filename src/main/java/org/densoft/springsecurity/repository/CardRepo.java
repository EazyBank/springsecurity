package org.densoft.springsecurity.repository;

import org.densoft.springsecurity.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepo extends JpaRepository<Card, Integer> {
    List<Card> findByCustomerId(int customerId);
}
