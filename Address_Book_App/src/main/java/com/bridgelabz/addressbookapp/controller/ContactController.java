package com.bridgelabz.addressbookapp.controller;

import com.bridgelabz.addressbookapp.model.Contact;
import com.bridgelabz.addressbookapp.service.ContactService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {
    private final ContactService service;

    public ContactController(ContactService service){
        this.service = service;
    }

    @GetMapping
    public List<Contact>getAllContacts(){
        return service.getAllContacts();
    }

    @GetMapping("/{id}")
    public Contact getContact(@PathVariable Long id){
        return service.getContactById(id);
    }

    @PostMapping
    public Contact addContact(@RequestBody Contact contact){
        return service.addContact(contact);
    }

    @PutMapping("/{id}")
    public Contact updateContact(@PathVariable Long id, @RequestBody Contact contact){
        return service.updatrContact(id,contact);
    }

    @DeleteMapping("/{id}")
    public void deleteContact(@PathVariable Long id){
        service.deleteContact(id);
    }
}
