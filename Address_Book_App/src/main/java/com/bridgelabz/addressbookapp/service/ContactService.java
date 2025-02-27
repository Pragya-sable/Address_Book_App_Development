package com.bridgelabz.addressbookapp.service;

import com.bridgelabz.addressbookapp.dto.ContactDTO;
import com.bridgelabz.addressbookapp.model.Contact;
import com.bridgelabz.addressbookapp.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Slf4j   // Lombok annotation to enable logging
@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;

    // GET all contacts
    public List<Contact> getAllContacts() {
        log.info("Fetching all contacts from the database");
        return contactRepository.findAll();
    }

    // GET contact by ID
    public Optional<Contact> getContactById(Long id) {
        log.debug("Fetching contact with ID: {}", id);
        return contactRepository.findById(id);
    }

    // POST - Add a contact
    public Contact addContact(ContactDTO contactDTO) {
        Contact contact = new Contact(null, contactDTO.getName(), contactDTO.getEmail(),
                contactDTO.getPhone(), contactDTO.getAddress());
        Contact savedContact = contactRepository.save(contact);
        log.info("New contact added: {}", savedContact);
        return savedContact;
    }

    // PUT - Update a contact by ID
    public Contact updateContact(Long id, ContactDTO contactDTO) {
        log.debug("Updating contact with ID: {}", id);
        return contactRepository.findById(id).map(existingContact -> {
            existingContact.setName(contactDTO.getName());
            existingContact.setEmail(contactDTO.getEmail());
            existingContact.setPhone(contactDTO.getPhone());
            existingContact.setAddress(contactDTO.getAddress());
            log.info("Updated contact: {}", existingContact);
            return contactRepository.save(existingContact);
        }).orElseThrow(() -> {
            log.error("Contact with ID {} not found!", id);
            return new RuntimeException("Contact not found!");
        });
    }

    // DELETE - Remove a contact by ID
    public void deleteContact(Long id) {
        log.warn("Deleting contact with ID: {}", id);
        contactRepository.deleteById(id);
    }

}
