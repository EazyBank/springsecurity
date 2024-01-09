package org.densoft.springsecurity.repository;

import org.densoft.springsecurity.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, String> {
}