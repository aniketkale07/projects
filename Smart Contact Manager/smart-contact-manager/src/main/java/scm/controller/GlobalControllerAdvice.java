package scm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import scm.entity.User;
import scm.service.UserService;

@ControllerAdvice
public class GlobalControllerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(GlobalControllerAdvice.class);

    @Autowired
    private UserService userService;

    @ModelAttribute
    public void checkUserIsLogged( Authentication authentication, Model model) {
      
if(authentication==null){
    return;
}

        if (authentication.isAuthenticated()) {
            String username = scm.helper.Helper.getLoggedUserEmail(authentication).toString();
          User user=userService.findUserByEmail(username);
          System.out.println("" + username);
          System.out.println(user);
          model.addAttribute("loggedUser", user);
        } 
    }
}
