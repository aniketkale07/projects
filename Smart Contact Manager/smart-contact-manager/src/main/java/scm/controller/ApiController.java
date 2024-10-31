package scm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import scm.entity.Contact;
import scm.service.ContactService;

@RestController
@RequestMapping("/api")
public class ApiController {
    
@Autowired
ContactService contactService;

    @GetMapping("/contact/{contactId}")
    public Contact getContact(@PathVariable Long contactId){
        return contactService.getContact(contactId);
    }


    // in display page it will help to delete the contact direct ffrom list
    @GetMapping("/contact/deleteContact")
    public String deleteContact(@PathVariable Long contactId){
        return "message";
    }
    
}
