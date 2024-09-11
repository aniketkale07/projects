package scm.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import scm.entity.Contact;
import scm.entity.User;
import scm.form.ResetForm;
import scm.helper.Helper;
import scm.helper.Message;
import scm.helper.MessageType;
import scm.service.ContactService;
import scm.service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ContactService contactService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    // User Dashboard
    @GetMapping("/user/dashboard")
    public String userDashboard() {
        return "/user/dashboard";
    }

    // Delete Contact
    @GetMapping("user/deletecontact")
    public String deleteContact(Model model) {
        return "user/deletecontact";
    }

    @GetMapping("user/deletecontactDB")
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

        return "user/deletecontact"; // Return the success page
    }

    // Add Contact
    @GetMapping("user/addcontact")
    public String addContact(Model model) {
        model.addAttribute("addContact", new Contact());
        return "user/addcontact";
    }

    // Display Contact
    @GetMapping("user/displaycontact")
    public String displayContact(Authentication authentication, Model model) {

        String email = Helper.getLoggedUserEmail(authentication);

        User user = userService.findUserByEmail(email);

        List<Contact> contacts = user.getContact();

        model.addAttribute("contacts", contacts);

        return "user/displaycontact";
    }

    // User Profile
    @GetMapping("user/profile")
    public String userProfile() {
        return "user/profile";
    }

    // Contact Profile
    @GetMapping("user/contactprofile")
    public String contactProfile() {
        return "user/contactprofile";
    }

    // Add Contact to DB
    @PostMapping("/user/addContactDB")
    public String addContactToDataBase(
            @ModelAttribute("addContact") Contact addContact,
            Authentication authentication,
            HttpSession session) {

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
                return "redirect:/user/addcontact";
            }

            // Create a new Contact and populate fields
            addContact.setUser(user); // Associate the contact with the user

            // Save the contact in the DB
            contactService.saveContact(addContact);

            // Log the contact save action
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

        return "redirect:/user/addcontact";
    }

    // Forget Password Handler
    @GetMapping("user/forgetpassword")
    public String forgetPassword(Model model, Authentication authentication) {
        model.addAttribute("resetForm", new ResetForm());
        return "user/forgetpassword";
    }

    // Reset Password
    @PostMapping("/resetPassword")
    public String resetPassword(
            @ModelAttribute("resetForm") ResetForm resetForm,
            HttpSession session,
            Authentication authentication) {

        String email = Helper.getLoggedUserEmail(authentication);
        User user = userService.findUserByEmail(email);
        String dbEmail = user.getEmail();
        String resetEmail = resetForm.getEmail().strip();
        String resetPassword = resetForm.getPassword().strip();

        if (!resetEmail.equals(dbEmail)) {
            logger.error("Email is Invalid");
            return "redirect:/forgetpassword";
        } else {
            String dbPassword = user.getPassword();

            // Check if the new password is the same as the old one
            if (dbPassword.equals(resetPassword)) {
                logger.error("New Password is the same as the old one");
                return "redirect:/forgetpassword";
            } else {
                user.setPassword(resetPassword); // Update DB password to resetForm's password
                userService.saveUser(user);
            }
        }
        return "redirect:/login";
    }

    @PostMapping("user/editContact")
    public String editContact(){

        return null;
    }
}
