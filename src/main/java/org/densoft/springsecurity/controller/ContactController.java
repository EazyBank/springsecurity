package org.densoft.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {

    @GetMapping("/contact")
    public String getLoans() {
        return "inquiry details are saved to the DB";
    }
}
