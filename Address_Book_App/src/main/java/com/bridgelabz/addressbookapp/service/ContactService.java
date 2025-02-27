package com.bridgelabz.addressbookapp.service;

import com.bridgelabz.addressbookapp.dto.ContactDTO;
import com.bridgelabz.addressbookapp.model.Contact;
import com.bridgelabz.addressbookapp.repository.ContactRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactService {
     private final ContactRepository repository;

     public ContactService(ContactRepository repository){
         this.repository = repository;
     }

    // CONVERT ENTITY TO DTO
    private ContactDTO convertToDTO(Contact contact){
         return new ContactDTO(contact.getName(),contact.getEmail(),contact.getPhone(),contact.getAddress());
    }
    
    // CONVERT DTO TO ENTITY
    private Contact convertToEntity(ContactDTO contactDTO){
         return  new Contact(null, contactDTO.getName(),contactDTO.getEmail(),contactDTO.getPhone(),contactDTO.getAddress());
    }

    // GET All Contacts (Return List of DTOs)
    public List<ContactDTO> getAllContacts() {
        return repository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // GET Contact by ID
    public ContactDTO getContactById(Long id) {
        return repository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Contact not found"));
    }


    // POST - ADD A CONTACT
    public ContactDTO addContact(ContactDTO contactDTO){
         Contact contact = repository.save(convertToEntity(contactDTO));
         return convertToDTO(contact);
    }

    // PUT - Update Contact by ID
    public ContactDTO updateContact(Long id, ContactDTO contactDTO) {
        Contact existingContact = repository.findById(id).orElseThrow(() -> new RuntimeException("Contact not found"));
        existingContact.setName(contactDTO.getName());
        existingContact.setEmail(contactDTO.getEmail());
        existingContact.setPhone(contactDTO.getPhone());
        existingContact.setAddress(contactDTO.getAddress());
        return convertToDTO(repository.save(existingContact));
    }

    // DELETE - Remove Contact by ID
    public void deleteContact(Long id) {
        repository.deleteById(id);
    }

}
