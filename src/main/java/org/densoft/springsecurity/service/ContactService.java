package org.densoft.springsecurity.service;

import org.densoft.springsecurity.model.Contact;
import org.densoft.springsecurity.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Random;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public Contact saveContactInquiryDetails(Contact contact) {
        contact.setContactId(getServiceReqNumber());
        contact.setCreateDt(new Date(System.currentTimeMillis()));
        return contactRepository.save(contact);
    }

    private String getServiceReqNumber() {
        Random random = new Random();
        int ranNumber = random.nextInt(999999999 - 9999) + 9999;
        return "SR%d".formatted(ranNumber);
    }


}
