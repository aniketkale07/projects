package scm.service;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import scm.entity.Contact;
import scm.entity.User;
import scm.helper.ResourceNotFoundException;
import scm.repository.UserRepository;

/**
 * Service class for user-related operations.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Saves a new user in the database.
     * 
     * @param user the user to be saved
     * @return the saved user
     */
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Retrieves a user by their ID.
     * 
     * @param userId the ID of the user to retrieve
     * @return an Optional containing the user, or an empty Optional if not found
     */
    @Override
    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    /**
     * Retrieves a user by their email.
     * 
     * @param email the email of the user to retrieve
     * @return the user, or null if not found
     */
    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Retrieves a list of all users.
     * 
     * @return a list of all users
     */
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Updates an existing user.
     * 
     * @param user the user to be updated
     * @return an Optional containing the updated user
     */
    @Override
    public Optional<User> updateUser(User user) {
        User userDB = userRepository.findById(user.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        // Set all fields from user to userDB
        userDB.setFirstName(user.getFirstName());
        userDB.setLastName(user.getLastName());
        userDB.setEmail(user.getEmail());
        userDB.setAbout(user.getAbout());
        userDB.setContact1(user.getContact1());
        userDB.setContact2(user.getContact2());
        userDB.setAbout(user.getAbout());
        userDB.setMiddleName(user.getMiddleName());
        userDB.setProfilePhoto(user.getProfilePhoto());
        userDB.setEnabled(user.isEnabled());
        userDB.setGmailVerified(user.isGmailVerified());
        // userDB.phoneVerified(user.isPhoneVerified());

        // Save the updated user to the database
        User updatedUser = userRepository.save(userDB);

        return Optional.of(updatedUser);
    }

    /**
     * Deletes a user by their ID.
     * 
     * @param userId the ID of the user to delete
     */
    @Override
    public void deleteUserById(Long userId) {
        userRepository.delete(userRepository.findById(userId).orElseThrow());
    }

    /**
     * Saves a new contact.
     * 
     * @param contact the contact to be saved
     * @return the saved contact
     */
    @Override
    public void saveContact(Contact contact) {
        throw new UnsupportedOperationException("Unimplemented method 'saveContact'");
    }

    /**
     * Retrieves a contact by their ID.
     * 
     * @param contactId the ID of the contact to retrieve
     * @return the contact, or null if not found
     */
    @Override
    public Contact getContact(Long contactId) {
        throw new UnsupportedOperationException("Unimplemented method 'getContact'");
    }

    /**
     * Updates an existing contact.
     * 
     * @param contact the contact to be updated
     * @return the updated contact
     */
    @Override
    public Contact updateContact(Contact contact) {
        throw new UnsupportedOperationException("Unimplemented method 'updateContact'");
    }

    /**
     * Deletes a contact.
     * 
     * @param contact the contact to be deleted
     * @return the deleted contact
     */
    @Override
    public Contact deleteContact(Contact contact) {
        throw new UnsupportedOperationException("Unimplemented method 'deleteContact'");
    }

    /**
     * Adds a new contact.
     * 
     * @param contact the contact to be added
     * @return the added contact
     */
    @Override
    public Contact addContact(Contact contact) {
        return contact;
    }

    /**
     * Retrieves a list of contacts for a specific user.
     * 
     * @param userId the ID of the user to retrieve contacts for
     * @return a list of contacts
     */
// Display Contact of specific user
/**
 * Retrieves a list of contacts for a specific user.
 * 
 * @param userId the ID of the user to retrieve contacts for
 * @return a list of contacts
 * 
 * Example: UserServiceImpl userService = new UserServiceImpl();
 *          List<Contact> contacts = userService.displayAllContact(1L);
 */
@Override
public List<Contact> displayAllContact(Long userId) {
    
    User user=userRepository.findById(userId).orElseThrow();
    List<Contact> contact =user.getContact();
    return contact;
}


/**
 * Checks if a user with a specific email exists.
 * 
 * @param email the email to check
 * @return true if the user exists, false otherwise
 * 
 * Example: UserServiceImpl userService = new UserServiceImpl();
 *          boolean exists = userService.isEmailExist("john.doe@example.com");
 */
@Override
public boolean isEmailExist(String email) {
    User userDB = userRepository.findByEmail(email);
    return userDB!=null;
}


/**
 * Checks if a user with a specific ID exists.
 * 
 * @param userId the ID to check
 * @return true if the user exists, false otherwise
 * 
 * Example: UserServiceImpl userService = new UserServiceImpl();
 *          boolean exists = userService.isUserExist(1L);
 */
@Override
public boolean isUserExist(long userId) {
    return userRepository.findById(userId).isPresent();
}
}