package scm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import scm.helper.ResourceNotFoundException;

import java.util.Optional;
import scm.repository.UserRepository;

@Service
public class SecurityCustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
       return userRepository.findByEmail(username)
       .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
    
}