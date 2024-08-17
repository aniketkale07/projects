package scm.service;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import scm.entity.Contact;
import scm.entity.User;
import scm.helper.ResourceNotFoundException;
import scm.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public User saveUser(User user) {
        // Long userId=UUID.randomUUID().toString();
        // user.setUserId(userId);


       
        user.setRoleList(List.of("ROLE_USER"));
        logger.info(user.getProviders().toString());
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

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
        userDB.setGmailVerified(user.isGmailVerified());
        // userDB.phoneVerified(user.isPhoneVerified());

        User updatedUser = userRepository.save(userDB);

        return Optional.of(updatedUser);
    }

    @Override
    public void deleteUserById(Long userId) {
        userRepository.delete(userRepository.findById(userId).orElseThrow());
    }

    @Override
    public void saveContact(Contact contact) {
        throw new UnsupportedOperationException("Unimplemented method 'saveContact'");
    }

    @Override
    public Contact getContact(Long contactId) {
        throw new UnsupportedOperationException("Unimplemented method 'getContact'");
    }

    @Override
    public Contact updateContact(Contact contact) {
        throw new UnsupportedOperationException("Unimplemented method 'updateContact'");
    }

    @Override
    public Contact deleteContact(Contact contact) {
        throw new UnsupportedOperationException("Unimplemented method 'deleteContact'");
    }

    @Override
    public Contact addContact(Contact contact) {
        return contact;
    }

    @Override
    public List<Contact> displayAllContact(Long userId) {
        User user=userRepository.findById(userId).orElseThrow();
        List<Contact> contact =user.getContact();
        return contact;
    }

    @Override
    public boolean isEmailExist(String email) {
        Optional<User> userDB = userRepository.findByEmail(email);
        return userDB!=null;
    }

    @Override
    public boolean isUserExist(long userId) {
        return userRepository.findById(userId).isPresent();
    }
}