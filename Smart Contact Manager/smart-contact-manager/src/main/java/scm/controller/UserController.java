package scm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.servlet.http.HttpSession;
import scm.entity.User;
import scm.form.ResetForm;
import scm.helper.Helper;
import scm.service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    // User Dashboard
    @GetMapping("/user/dashboard")
    public String userDashboard() {
        return "/user/dashboard";
    }

    // Delete Contact
   

   
    // User Profile
    @GetMapping("user/profile")
    public String userProfile() {
        return "user/profile";
    }

    // Contact Profile
   
    // Add Contact to DB
    

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

   
}
