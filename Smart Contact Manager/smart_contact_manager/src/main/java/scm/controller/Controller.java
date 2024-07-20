package scm.controller;

import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller {
    
    @GetMapping("login")
    public String login(){
        return "login";
    }
}
