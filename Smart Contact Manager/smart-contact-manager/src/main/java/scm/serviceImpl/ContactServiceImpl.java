package scm.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import scm.entity.Contact;
import scm.repository.ContactRepository;
import scm.service.ContactService;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    ContactRepository repository;
   

    @Override
    public void updateContact(Contact contact) {
       repository.save(contact);
    }


    @Override
    public List<Contact> displayAllContact() {
        return repository.findAll();
        
       
    }

    @Override
    public void saveContact(Contact contact) {
        
         repository.save(contact);
      
    }



    @Override
    public void deleteContact(Contact contact) {
        
        repository.delete(contact);
    }


    @Override
    public Contact getContact(Long contactId) {
     
       return repository.findById(contactId).get();

    }

   
    
}
