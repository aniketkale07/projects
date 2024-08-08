package scm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import scm.entity.User;
import scm.form.ContactForm;
import scm.form.LoginForm;
import scm.form.UserRegistration;
import scm.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;




@org.springframework.stereotype.Controller
public class Controller {
    

    @Autowired(required = true)
    private UserService userService;


// -------------------------- Model -------------------------------



    @GetMapping("/home")
    public String userLogin(Model model){
        return "home";
    }

    //Create a new user in web
    @GetMapping("/signup")
    public String userSignup(  Model model){
        
        model.addAttribute("userRegistration", new UserRegistration());
        return "signup";
    }

@GetMapping("/login")
public String login(Model model) {
   model.addAttribute("loginForm", new LoginForm("xyz@gmail.com", null));
    return new String("login");
}

    @GetMapping("/about")
    public String about() {
        return new String("about");
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("contactForm", new ContactForm());
        return new String("contact-us");
    }

    @GetMapping("/service")
    public String service() {
        return new String("service");
    }

@GetMapping("/thanks")
public String thanksPage() {
    return new String("thanks-page");
}


    // -------------------- CREATE NEW USER------------------
    @PostMapping("/new-user")
    public String newUser( @ModelAttribute("userReg") UserRegistration userReg) {
       
        //TODO: process POST request
        // Processing Before Saving the user to database
        // Check the User is alredy availble in DB or not
        // if user is already availble in DB. then --> Invalid user
        // Otherwise save in DB and Redirect the user to login page
        // Add Encryption to password
        //Create new User 
        User user=new User();
        String email=userReg.getEmail().strip();

        //Retrive user from DB
        User dbUser = userService.getByEmail(email);

        if(email==dbUser.getEmail()){
            System.out.println("Duplicate User Email..");
        }else{
            String encryptedPasswd=userReg.getPassword();
            
            user.setFName(userReg.getFName());
            user.setMName(userReg.getMName());
            user.setLName(userReg.getLName());

            user.setAbout(userReg.getAbout());
            user.setEmail(userReg.getEmail());

            user.setContact1(userReg.getContact1());
            user.setContact2(userReg.getContact2());
            user.setProfilePhoto(userReg.getProfilePhoto());

            System.out.println("User Registration Successfully");
        }
        return "redirect:/login" ;   
     }
    





     
    // ----------------- AUTHORIZATION -------------------------------------
    
    
    //-------------Check Login-----------------------
    // @GetMapping("/checklogin")
    // public String checkLogin(@Valid  @ModelAttribute("userLogin") UserLogin userLogin, BindingResult bindingResult, HttpSession session) {
    //     //TODO: process POST request
    //     return "redirect:/home" ;
    // }
    


    // Contact us form BACKEND   ------------- form submit here-------
    @PostMapping("/submit-contact-form")
    public String submitContactform(@ModelAttribute("contactform") ContactForm contactForm){
        System.out.println(contactForm);
        System.out.println("Form Submit");
        return "redirect:/thanks";

    }
}


