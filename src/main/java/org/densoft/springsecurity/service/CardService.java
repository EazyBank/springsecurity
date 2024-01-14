package org.densoft.springsecurity.service;

import org.densoft.springsecurity.model.Card;
import org.densoft.springsecurity.repository.CardRepo;
import org.densoft.springsecurity.repository.CustomerRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {

    private final CardRepo cardRepo;
    private final CustomerRepo customerRepo;

    public CardService(CardRepo cardRepo, CustomerRepo customerRepo) {
        this.cardRepo = cardRepo;
        this.customerRepo = customerRepo;
    }

    public List<Card> getCards(String email) {
        return customerRepo.findByEmail(email)
                .map(customer -> cardRepo.findByCustomerId(customer.getId()))
                .orElseThrow(() -> new RuntimeException("user not found with email: " + email));
    }
}
