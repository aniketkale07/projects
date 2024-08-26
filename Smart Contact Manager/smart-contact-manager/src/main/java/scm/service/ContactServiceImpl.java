package scm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import scm.entity.Contact;
import scm.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    ContactRepository repository;
    @Override
    public void addContact(Contact contact) {
        
        repository.save(contact);
    }

    @Override
    public void updateContact(Contact Contact) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateContact'");
    }

    @Override
    public void deleteContact(long contactId) {
        // TODO Auto-generated method stub
        repository.deleteById(contactId);
       
    }

    @Override
    public List<Contact> displayAllContact(Long userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'displayAllContact'");
    }

    @Override
    public void saveContact(Contact contact) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveContact'");
    }

    @Override
    public Contact getContact(Long contactId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getContact'");
    }
    
}
