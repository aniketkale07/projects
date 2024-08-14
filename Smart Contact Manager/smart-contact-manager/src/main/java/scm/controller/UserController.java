package scm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.servlet.http.HttpSession;
import scm.entity.User;
import scm.form.AddContactForm;
import scm.form.ResetForm;
import scm.service.UserService;




@Controller
public class UserController {
    
    @Autowired
    UserService userService;

private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    //User -Home 
    @GetMapping("/user/home")
    public String userHome() {
        logger.info("THis is User Home..");
        return new String("user/userhome");
    }
    
    // Delete Contact
    @GetMapping("/user/deletecontact")
    public String deleteContact(Model model){
        return "user/deletecontact";
    }

    // Add Contact
    @GetMapping("/user/addcontact")
    public String addContact(Model model){
        model.addAttribute("addContact", new AddContactForm());
        return "user/addcontact";
    }

    // Display Contact
    @GetMapping("/user/displaycontact")
    public String displayContact() {
        return new String("user/displaycontact");
    }
    
    // User Profile
    @GetMapping("/user/profile")
    public String userProfile() {
        return new String("user/userprofile");
    }

    // Contact Profile
    @GetMapping("/user/contactprofile")
    public String contactProfile() {
        return new String("user/contactProfile");
    }
    


    // Add Contact to DB
@PostMapping("/user/addContactDB")
public String addContactToDataBase(@ModelAttribute("addContact") AddContactForm addContact){
    System.out.println(addContact);
    return null;   
}

    //Forget Password
    // ----> 1. check user is validated
    // ----> 2.check the new Password is same or different 
    //  ----->3. if password is same Warn the user and say for the change the password
    // ----> 4.update the password in database
    //  ----> 5.redirect the user to login page for login with new passwordd

    @GetMapping("/user/forgetpassword")
    public String forgetPassword(Model model){
        return new String("forgetpassword");
    }

    // Controller for the Reset password 
    //------> it chage the password from database
    @PostMapping("/resetPassword")
    public String resetPassword(@ModelAttribute("resetForm") ResetForm resetForm, HttpSession session){

        //lets User is Logged user in System
        // get email from logged user account
        // check the email is valid or not i.e DBEMAIL and RESETFORM email are equal
        // get user password from that valid user email and check it remail same or not
        // if both are same then ==> warn about same password
        //  change the database value
        User user= new User();  // This User is getting Temparory Purpose

        String dbEmail=user.getEmail();

        String resetEmail=resetForm.getEmail().strip();
        String resetPassword=resetForm.getPassword().strip();

        if(resetEmail!=dbEmail){
            System.out.println("Email is Invalid ..");
            return "redirect:/forgetpassword";
        }else{
            String dbPassword=user.getPassword();
            
            // -----> check the dbPassword is match
            if(dbPassword==resetPassword){
                return "redirect:/forgetpassword";
            }else{
                user.setPassword(resetPassword);  // update DB password to reset Form Password
                // userService.saveUser(user);
            }
        }
        return "redirect:/login";
    }
    
}
