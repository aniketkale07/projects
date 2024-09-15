package scm.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import scm.entity.Contact;
import scm.entity.User;
import scm.form.AddContactForm;
import scm.form.ContactForm;
import scm.helper.Helper;
import scm.helper.Message;
import scm.helper.MessageType;
import scm.service.ContactService;
import scm.service.ImageService;
import scm.service.UserService;

@Controller
@RequestMapping("/user/contact")
public class ContactController {

    @Autowired
    private UserService userService;

    @Autowired
    private ContactService contactService;

    @Autowired
    private ImageService imageService;

    private static final Logger logger = LoggerFactory.getLogger(ContactController.class);

    @GetMapping("/deletecontact")
    public String deleteContact(Model model) {
        return "user/deletecontact";
    }

    @GetMapping("/deletecontactDB")
    public String deleteContactDB(@RequestParam(value = "contactId", required = false) Long contactId,
            HttpSession session) {

        Contact contactDB = contactService.getContact(contactId).get();
        if (contactDB == null) {
            // Handle the case where the contactId is null, for example, return an error
            // page

            Message message = Message.builder()
                    .content("Contact not found")
                    .type(MessageType.red)
                    .build();
            session.setAttribute("message", message);
            return "user/deletecontact"; // Redirect to an appropriate error page or handle accordingly
        }

        // Proceed with deleting the contact since contactId is not null
        contactService.deleteContact(contactDB);
        Message message = Message.builder()
                .content("Contact deleted Successfully")
                .type(MessageType.green)
                .build();
        session.setAttribute("message", message);

        return "/user/deletecontact"; // Return the success page
    }

    // Add Contact
    @GetMapping("/addcontact")
    public String addContact(Model model) {
        AddContactForm addContactForm = new AddContactForm();

        model.addAttribute("addContactForm", addContactForm);
        return "user/addcontact";
    }

    @PostMapping("/addContactDB")
    public String addContactToDataBase(@Valid @ModelAttribute("addContactForm") AddContactForm addContactForm,
            BindingResult result, Authentication authentication, HttpSession session) {
    
        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> logger.info(error.toString()));
            Message message = Message.builder()
                    .content("Check the fields of the form..")
                    .type(MessageType.red)
                    .build();
            session.setAttribute("message", message);
            return "user/addcontact"; // If errors, go back to the form
        }
    
        // if image is empty 
        // if (addContactForm.getContactImage() == null || addContactForm.getContactImage().isEmpty()) {
        //     logger.info("Contact image is empty...");
        //     Message message = Message.builder()
        //             .content("Please select a contact image.")
        //             .type(MessageType.red)
        //             .build();
        //     session.setAttribute("message", message);
        //     return "user/addcontact"; // Return to the form with an error message
        // }
    
        // String fileURl = imageService.uploadImage(addContactForm.getContactImage());
    
        Contact contact = new Contact();
    
        try {
            // Get authenticated user's email
            String email = Helper.getLoggedUserEmail(authentication);
    
            User user = userService.findUserByEmail(email);
    
            contact.setName(addContactForm.getName());
            contact.setAbout(addContactForm.getAbout());
            contact.setEmail(addContactForm.getEmail());
            contact.setGithub(addContactForm.getGithub());
            contact.setLinkedin(addContactForm.getLinkedin());
            contact.setPrimaryContact(addContactForm.getPrimaryContact());
            
            // contact.setContactImageUrl(fileURl);
            contact.setUser(user);
            // Log the contact save action
    
            contactService.saveContact(contact);
    
            logger.info("Contact saved for user: {}", user.getEmail());
            Message message = Message.builder()
                    .content("Contact added successfully")
                    .type(MessageType.green)
                    .build();
            session.setAttribute("message", message);
    
        } catch (Exception e) {
            logger.error("Error saving contact", e);
            Message message = Message.builder()
                    .content(e + "Error adding contact. Please try again.")
                    .type(MessageType.red)
                    .build();
            session.setAttribute("message", message);
        }
        return "user/addcontact";
    }
    // Display Contact
    @GetMapping("/displaycontact")
    public String displayContact(Authentication authentication, Model model) {

        String email = Helper.getLoggedUserEmail(authentication);

        User user = userService.findUserByEmail(email);

        List<Contact> contacts = user.getContact();

        model.addAttribute("contacts", contacts);

        return "user/displaycontact";
    }

    @GetMapping("/contactprofile")
    public String contactProfile() {
        return "user/contactprofile";
    }

    @PostMapping("/editContact")
    public String editContact() {

        return null;
    }
}
