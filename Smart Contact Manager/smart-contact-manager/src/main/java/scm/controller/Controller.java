package scm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import ch.qos.logback.core.model.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;




@org.springframework.stereotype.Controller
public class Controller {
    
    @GetMapping("/home")
    public String userLogin(Model model){
        return "home";
    }

    //Create a new user in web
    @GetMapping("/signup")
    public String userSignup(Model model){
        return "get-started";
    }

@GetMapping("/login")
public String login() {
    return new String("login");
}


    @GetMapping("about")
    public String about() {
        return new String("about");
    }

    @GetMapping("contact")
    public String contact() {
        return new String("contact-us");
    }

    @GetMapping("feedback")
    public String feedbackForm() {
        return new String("feedback-form");
    }
    

    // ----------------- AUTHORIZATION -------------------------------------
    //-------------Check Login-----------------------
    @GetMapping("checklogin")
    public String checkLogin(@RequestBody String entity) {
        //TODO: process POST request
        
        return "userhome";
    }
    
    //-------------------- CREATE NEW USER------------------
    @PostMapping("newuser")
    public String newUser(@RequestBody String entity, String string) {
        //TODO: process POST request
        
        return new String("userhome");
    }
    
}


