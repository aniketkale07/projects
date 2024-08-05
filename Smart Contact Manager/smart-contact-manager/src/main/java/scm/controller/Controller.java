package scm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import scm.entity.User;
import scm.form.UserRegistration;
import scm.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;



@org.springframework.stereotype.Controller
public class Controller {
    

    @Autowired(required = true)
    private UserService userService;



    @GetMapping("/home")
    public String userLogin(Model model){
        return "home";
    }

    //Create a new user in web
    @GetMapping("/signup")
    public String userSignup(  Model model){
        return "signup";
    }

@GetMapping("/login")
public String login(org.springframework.ui.Model model) {
   
    return new String("login");
}


    @GetMapping("/about")
    public String about() {
        return new String("about");
    }

    @GetMapping("/contact")
    public String contact() {
        return new String("contact-us");
    }

    @GetMapping("/feedback")
    public String feedbackForm() {
        return new String("feedback-form");
    }
    

    // -------------------- CREATE NEW USER------------------
    @PostMapping("/new-user")
    public String newUser(@Valid @ModelAttribute("user") UserRegistration user, BindingResult bindingResult) {
       
        //TODO: process POST request
        // Processing Before Saving the user to database
        // Check the User is alredy availble in DB or not
        // if user is already availble in DB. then --> Invalid user
        // Otherwise save in DB and Redirect the user to login page
        // Add Encryption to password

        String email=user.getEmail().strip();

        User dbUser = userService.getByEmail(email);

        if(email==dbUser.getEmail()){
            System.out.println("Duplicate User Email..");
        }else{
            String encryptedPasswd=user.getPassword();
            
            System.out.println("User Registration Successfully");
        }
        
        System.out.println(user);
        return "redirect:/login" ;   
     }
    

     
    // ----------------- AUTHORIZATION -------------------------------------
    //-------------Check Login-----------------------
    // @GetMapping("/checklogin")
    // public String checkLogin(@Valid  @ModelAttribute("user") User user, BindingResult bindingResult, HttpSession session) {
    //     //TODO: process POST request
    //     return "redirect:/home" ;
    // }
    
}


