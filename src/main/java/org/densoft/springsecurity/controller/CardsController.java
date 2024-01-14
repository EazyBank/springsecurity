package org.densoft.springsecurity.controller;

import org.densoft.springsecurity.model.Card;
import org.densoft.springsecurity.service.CardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CardsController {

    private final CardService cardService;

    public CardsController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/myCards")
    public List<Card> getCardDetails(@RequestParam String email) {
        return cardService.getCards(email);
    }
}
