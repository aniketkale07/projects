package scm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import scm.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    
}
