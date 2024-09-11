package scm.service;

import java.util.List;
import java.util.Optional;

import scm.entity.Contact;

public interface ContactService {

    
    void updateContact(Contact Contact);
    void deleteContact(Contact contact);
    List<Contact> displayAllContact();
     // // Save Contact
    void saveContact(Contact contact);

    // // Get Single Contact
    Optional<Contact> getContact(Long contactId);
    
}
