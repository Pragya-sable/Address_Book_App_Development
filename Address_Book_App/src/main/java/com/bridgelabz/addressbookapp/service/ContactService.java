package com.bridgelabz.addressbookapp.service;

import com.bridgelabz.addressbookapp.model.Contact;
import com.bridgelabz.addressbookapp.repository.ContactRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ContactService {
     private final ContactRepository repository;

     public ContactService(ContactRepository repository){
         this.repository = repository;
     }

     public List<Contact> getAllContacts(){
         return repository.findAll();
     }

    public Contact getContactById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Contact not found"));
    }

    public Contact addContact(Contact contact) {
        return repository.save(contact);
    }

     public Contact updatrContact(Long id,Contact contactDetails){
         Contact contact = getContactById(id);
         contact.setName(contactDetails.getName());
         contact.setEmail(contactDetails.getEmail());
         contact.setPhone(contactDetails.getPhone());
         contact.setAddress(contactDetails.getAddress());
         return repository.save(contact);
     }
    public void deleteContact(Long id) {
        repository.deleteById(id);
    }

}
