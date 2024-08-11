package scm.service;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Optional;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import scm.entity.Contact;
import scm.entity.User;
import scm.helper.ResourceNotFoundException;
import scm.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService {


@Autowired
UserRepository userRepository;

private Logger Logger = LoggerFactory.getLogger(this.getClass());

// Save User in database
// NEW USER REGISTRATION
@Override
public User saveUser(User user) {
    // TODO Auto-generated method stub
    
    return userRepository.save(user);
}



// Get User By ID
@Override
public Optional<User> getUserById(Long userId) {
    // TODO Auto-generated method stub
return userRepository.findById(userId);
}


@Override
public User findUserByEmail(String email) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findUserByEmail'");
}
@Override
public ArrayList<User> getAllUsers() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getAllUsers'");
}
@Override
public Optional<User> updateUser(User user) {
    User userDB = userRepository.findById(user.getUserId())
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));

    // Set all fields from user to userDB
    userDB.setFName(user.getFName());
    userDB.setLName(user.getLName());
    userDB.setEmail(user.getEmail());
    userDB.setAbout(user.getAbout());
    userDB.setContact1(user.getContact1());
    userDB.setContact2(user.getContact2());
    userDB.setAbout(user.getAbout());
    userDB.setMName(user.getMName());
    userDB.setProfilePhoto(user.getProfilePhoto());
    userDB.setEnabled(user.isEnabled());
    userDB.setGmailVerified(user.isGmailVerified());
    // userDB.phoneVerified(user.isPhoneVerified());

    // Save the updated user to the database
    User updatedUser = userRepository.save(userDB);

    return Optional.of(updatedUser);
}

@Override
public void deleteUserById(Long userId) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deleteUserById'");
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

@Override
public Contact updateContact(Contact contact) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'updateContact'");
}

@Override
public Contact deleteContact(Contact contact) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deleteContact'");
}

@Override
public Contact addContact(Contact contact) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'addContact'");
}

@Override
public ArrayList<Contact> displayAllContact(Long userId) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'displayAllContact'");
}




    
}
