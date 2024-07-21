package scm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class UserController {
    
    @GetMapping("/deletecontact")
    public String deleteContact(Model model){
        return "user/deletecontact";
    }

    @GetMapping("addcontact")
    public String addContact(){
        return "user/addcontact";
    }

    @GetMapping("displaycontact")
    public String displayContact() {
        return new String("user/displaycontact");
    }
    
    @GetMapping("profile")
    public String userProfile() {
        return new String("user/userprofile");
    }

    @GetMapping("contactprofile")
    public String contactProfile() {
        return new String("user/contactProfile");
    }
    
    
}
