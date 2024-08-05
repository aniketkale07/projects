package scm.service;

import java.net.http.HttpResponse;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import scm.entity.Contact;
import scm.entity.User;
import scm.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService {


@Autowired
UserRepository userRepository;

// Save User in database
// NEW USER REGISTRATION
@Override
public User saveUser(User user) {
    // TODO Auto-generated method stub
    
    return userRepository.save(user);
}


// Get User By ID
@Override
public User getUserById(Long userId) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getUserById'");
}

@Override
public User getByEmail(String email) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getByEmail'");
}

@Override
public ArrayList<User> getAllUsers() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getAllUsers'");
}

@Override
public User updateUser(User user) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
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
