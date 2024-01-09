package org.densoft.springsecurity.controller;

import org.densoft.springsecurity.model.Contact;
import org.densoft.springsecurity.service.ContactService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/contact")
    public Contact saveContactInquiryDetails(@RequestBody Contact contact) {
        return contactService.saveContactInquiryDetails(contact);
    }
}
