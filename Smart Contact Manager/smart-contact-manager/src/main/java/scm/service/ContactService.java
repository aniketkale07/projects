package scm.service;

import java.util.List;

import scm.entity.Contact;

public interface ContactService {

    void addContact(Contact contact);
    void updateContact(Contact Contact);
    void deleteContact(long contactId);
    List<Contact> displayAllContact(Long userId);
     // // Save Contact
    void saveContact(Contact contact);

    // // Get Single Contact
    Contact getContact(Long contactId);
    
}
