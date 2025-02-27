package com.bridgelabz.addressbookapp.controller;

import com.bridgelabz.addressbookapp.model.Contact;
import com.bridgelabz.addressbookapp.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {
    @Autowired
    private ContactService service;

    // GET All Contacts
    @GetMapping
    public ResponseEntity<List<Contact>> getAllContacts() {
        return ResponseEntity.ok(service.getAllContacts());
    }

    // GET Contact by ID
    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContact(@PathVariable Long id) {
        return service.getContactById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST - Add a Contact
    @PostMapping
    public ResponseEntity<Contact> addContact(@RequestBody Contact contact) {
        return ResponseEntity.ok(service.addContact(contact));
    }

    // PUT - Update Contact by ID
    @PutMapping("/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable Long id, @RequestBody Contact contact) {
        return ResponseEntity.ok(service.updateContact(id, contact));
    }

    // DELETE - Remove Contact by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteContact(@PathVariable Long id) {
        service.deleteContact(id);
        return ResponseEntity.ok("Contact deleted successfully");
    }
}
