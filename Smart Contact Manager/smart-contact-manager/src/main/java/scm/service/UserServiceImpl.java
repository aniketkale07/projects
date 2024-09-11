package scm.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import scm.entity.Contact;
import scm.entity.User;
import scm.exception.ResourceNotFoundException;
import scm.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    @Lazy
    PasswordEncoder passwordEncoder;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public User saveUser(User user) {
        // String userId=UUID.randomUUID().toString();
        // user.setUserId(userId);

        if (user.getPassword() == null || user.getPassword().isEmpty() || user.getPassword().isBlank()) {
            user.setPassword("");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        logger.info(user.getProviders().toString());
        return userRepository.save(user);
    }

    // find User by User ID
    @Override
    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    // Find User by Email
    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    // Get ALl User
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Update User
    @Override
    public Optional<User> updateUser(User user) {
        User userDB = userRepository.findById(user.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

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
        userDB.setEmailVerified(user.isEmailVerified());
        // userDB.phoneVerified(user.isPhoneVerified());

        User updatedUser = userRepository.save(userDB);

        return Optional.of(updatedUser);
    }

    @Override
    public void deleteUserById(Long userId) {
        userRepository.delete(userRepository.findById(userId)
                .orElseThrow(() ->
                 new ResourceNotFoundException("User Not Found with this " + userId)));
    }

    // @Override
    // public void saveContact(Contact contact) {
    //     throw new UnsupportedOperationException("Unimplemented method 'saveContact'");
    // }

    // @Override
    // public Contact getContact(Long contactId) {
    //     throw new UnsupportedOperationException("Unimplemented method 'getContact'");
    // }

    // @Override
    // public Contact updateContact(Contact contact) {
    //     throw new UnsupportedOperationException("Unimplemented method 'updateContact'");
    // }

    // @Override
    // public Contact deleteContact(Contact contact) {
    //     throw new UnsupportedOperationException("Unimplemented method 'deleteContact'");
    // }

    // @Override
    // public Contact addContact(Contact contact) {
    //     return contact;
    // }

    // @Override
    // public List<Contact> displayAllContact(Long userId) {
    //     User user = userRepository.findById(userId).orElseThrow();
    //     List<Contact> contact = user.getContact();
    //     return contact;
    // }

    @Override
    public boolean isEmailExist(String email) {
    User userDB = userRepository.findUserByEmail(email);
        return userDB != null ? true : false;
    }

    @Override
    public boolean isUserExist(long userId) {
        return userRepository.findById(userId).isPresent();
    }



    

}