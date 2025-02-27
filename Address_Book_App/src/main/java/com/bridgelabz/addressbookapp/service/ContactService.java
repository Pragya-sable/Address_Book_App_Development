package com.bridgelabz.addressbookapp.service;

import com.bridgelabz.addressbookapp.model.Contact;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ContactService {
    private final List<Contact> contactList = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1); // Simulates auto-increment ID

    // GET all contacts
    public List<Contact> getAllContacts() {
        return contactList;
    }

    // GET contact by ID
    public Optional<Contact> getContactById(Long id) {
        return contactList.stream().filter(c -> c.getId().equals(id)).findFirst();
    }

    // POST - Add a contact
    public Contact addContact(Contact contact) {
        contact.setId(idCounter.getAndIncrement()); // Simulate ID generation
        contactList.add(contact);
        return contact;
    }

    // PUT - Update a contact by ID
    public Contact updateContact(Long id, Contact contactDetails) {
        Optional<Contact> optionalContact = getContactById(id);
        if (optionalContact.isPresent()) {
            Contact existingContact = optionalContact.get();
            existingContact.setName(contactDetails.getName());
            existingContact.setEmail(contactDetails.getEmail());
            existingContact.setPhone(contactDetails.getPhone());
            existingContact.setAddress(contactDetails.getAddress());
            return existingContact;
        } else {
            throw new RuntimeException("Contact not found");
        }
    }

    // DELETE - Remove contact by ID
    public void deleteContact(Long id) {
        contactList.removeIf(contact -> contact.getId().equals(id));
    }
}
