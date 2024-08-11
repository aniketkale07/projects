package scm.service;

import java.util.ArrayList;
import java.util.Optional;

import scm.entity.Contact;
import scm.entity.User;


public interface UserService {
    // Save User
    User saveUser(User user);

    // Get User by Id
    Optional<User> getUserById(Long userId);

//Get User BY Gmail
User findUserByEmail(String email);

    // Display All User
    ArrayList<User> getAllUsers();

    // Update User
    Optional<User> updateUser(User user);

    // Delete User
    void deleteUserById(Long userId);

    // Methods for the Contact

    // Save Contact
    void saveContact(Contact contact);

    // Get Single Contact
    Contact getContact(Long contactId);

    // Update Contact
    Contact updateContact(Contact contact);

    // Delete Contact
    Contact deleteContact(Contact contact);

    // Add Contact
    Contact addContact(Contact contact);

    // Display All Contact
    ArrayList<Contact> displayAllContact(Long userId);
}