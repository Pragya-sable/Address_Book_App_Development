package com.bridgelabz.addressbookapp.controller;

import com.bridgelabz.addressbookapp.dto.ContactDTO;
import com.bridgelabz.addressbookapp.model.Contact;
import com.bridgelabz.addressbookapp.service.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@Slf4j
@RestController
@RequestMapping("/api/contacts")
public class ContactController {
    @Autowired
    private ContactService contactService;

    //  GET all contacts
    @GetMapping
    public ResponseEntity<List<Contact>> getAllContacts() {
        log.info("Received request to fetch all contacts");
        return ResponseEntity.ok(contactService.getAllContacts());
    }

    //  GET contact by ID
    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable Long id) {
        log.info("Received request to fetch contact with ID: {}", id);
        Optional<Contact> contact = contactService.getContactById(id);
        return contact.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST - Add a new contact
    @PostMapping
    public ResponseEntity<Contact> addContact(@RequestBody ContactDTO contactDTO) {
        log.info("Received request to add new contact: {}", contactDTO);
        Contact savedContact = contactService.addContact(contactDTO);
        return ResponseEntity.ok(savedContact);
    }

    // PUT - Update contact by ID
    @PutMapping("/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable Long id, @RequestBody ContactDTO contactDTO) {
        log.info("Received request to update contact with ID: {}", id);
        Contact updatedContact = contactService.updateContact(id, contactDTO);
        return ResponseEntity.ok(updatedContact);
    }

    //  DELETE - Remove contact by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
        log.warn("Received request to delete contact with ID: {}", id);
        contactService.deleteContact(id);
        return ResponseEntity.noContent().build();
    }
}
