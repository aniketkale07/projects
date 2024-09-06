package scm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import scm.entity.Contact;
import scm.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    ContactRepository repository;
   

    @Override
    public void updateContact(Contact contact) {
       repository.save(contact);
    }

    @Override
    public void deleteContact(long contactId) {
       
        repository.deleteById(contactId);
       
    }

    @Override
    public List<Contact> displayAllContact(Long userId) {
        
        return null;
       
    }

    @Override
    public void saveContact(Contact contact) {
         repository.save(contact);
      
    }

    @Override
    public Optional<Contact> getContact(Long contactId) {
        return repository.findById(contactId);
       
    }
    
}
