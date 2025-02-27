package com.bridgelabz.addressbookapp.service;

import com.bridgelabz.addressbookapp.dto.ContactDTO;
import com.bridgelabz.addressbookapp.model.Contact;
import com.bridgelabz.addressbookapp.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContactService {
    @Autowired
     private  ContactRepository repository;

    // GET all contacts
    public List<Contact> getAllContacts() {
        return repository.findAll();
    }

    // GET contact by ID
    public Optional<Contact> getContactById(Long id) {
        return repository.findById(id);
    }

    // POST - Add a contact
    public Contact addContact(Contact contact) {
        return repository.save(contact);
    }

    // PUT - Update a contact by ID
    public Contact updateContact(Long id, Contact contactDetails) {
        Contact existingContact = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contact not found"));
        existingContact.setName(contactDetails.getName());
        existingContact.setEmail(contactDetails.getEmail());
        existingContact.setPhone(contactDetails.getPhone());
        existingContact.setAddress(contactDetails.getAddress());
        return repository.save(existingContact);
    }

    // DELETE - Remove contact by ID
    public void deleteContact(Long id) {
        repository.deleteById(id);
    }

}
