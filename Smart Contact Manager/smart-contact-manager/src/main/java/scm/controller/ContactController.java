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


                logger.info("file information : {}", addContactForm.getContactImage().getOriginalFilename());

                String imgUrl = ImageService.uploadImage(addContactForm.getContactImage());

        if (result.hasErrors()) {
            return "user/addcontact"; // If errors, go back to the form
        }
        Contact contact = new Contact();

        try {
            // Get authenticated user's email
            String email = Helper.getLoggedUserEmail(authentication);

            if (email == null) {
                // Handle the case where authentication.getName() returns null
                // For example, redirect to login page or return an error message
                return "redirect:/login";
            }

            User user = userService.findUserByEmail(email);

            if (user == null) {
                // Handle the case where user is not found
                // For example, return an error message
                Message message = Message.builder()
                        .content("User not found" + email)
                        .type(MessageType.red)
                        .build();
                session.setAttribute("message", message);
                return "user/addcontact";
            }

            
            
            contact.setName(addContactForm.getName());
            contact.setAbout(addContactForm.getAbout());
            contact.setEmail(addContactForm.getEmail());
            contact.setGithub(addContactForm.getGithub());
            contact.setLinkedin(addContactForm.getLinkedin());
            contact.setPrimaryContact(addContactForm.getPrimaryContact());
            contact.setContactImage(addContactForm.getContactImage());
            contact.setUser(user);
            // Log the contact save action

            // contactService.saveContact(contact);


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
