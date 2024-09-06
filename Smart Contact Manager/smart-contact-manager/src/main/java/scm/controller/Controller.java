package scm.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import scm.entity.User;
import scm.form.ContactForm;
import scm.form.LoginForm;
import scm.form.UserRegistrationForm;
import scm.helper.Message;
import scm.helper.MessageType;
import scm.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.servlet.http.HttpSession;

@org.springframework.stereotype.Controller
public class Controller {

    private static final Logger logger = LoggerFactory.getLogger(Controller.class);

    @Autowired(required = true)
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // -------------------------- Model -------------------------------

    @GetMapping("/home")
    public String userLogin(Model model) {
        logger.info("THis is User Home");
        return "home";
    }

    // Create a new user in web
    @GetMapping("/signup")
    public String userSignup(Model model) {

        model.addAttribute("userRegistration", new UserRegistrationForm());
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
    public String newUser(@ModelAttribute("userReg") UserRegistrationForm userReg,
            HttpSession session, Model model) {

        // TODO: process POST request
        // Processing Before Saving the user to database
        // Check the User is alredy availble in DB or not
        // if user is already availble in DB. then --> Invalid user
        // Otherwise save in DB and Redirect the user to login page
        // Add Encryption to password
        // Create new User

        String email = userReg.getEmail().strip();
        User dbUser;
        // Retrieve user from DB
        try {

            dbUser = userService.findUserByEmail(email);

            if (dbUser!=null) {
                // User already exists, set error message
                Message message = Message.builder()
                        .content("Invalid Email: User already exists")
                        .type(MessageType.red)
                        .build();

                session.setAttribute("message", message);

                return "redirect:/signup"; // Return to signup page with a general error message

            } else {

                User user = new User();
                // Create and save new user
                user.setFirstName(userReg.getFName());
                user.setMiddleName(userReg.getMName());
                user.setLastName(userReg.getLName());
                user.setAbout(userReg.getAbout());
                user.setEmail(userReg.getEmail());
                user.setPassword(userReg.getPassword());
                user.setContact1(userReg.getContact1());
                user.setContact2(userReg.getContact2());
                user.setProfilePhoto(userReg.getProfilePhoto());
                user.setRoleList(List.of("ROLE_USER"));
                userService.saveUser(user);

                // Success message
                Message message = Message.builder()
                        .content("Registration Successful")
                        .type(MessageType.green)
                        .build();
                session.setAttribute("message", message);

                return "redirect:/login"; // Redirect to login page after successful registration
            }

        } catch (Exception e) {
            // TODO: handle exception
            logger.error("invalid email", e);
            e.printStackTrace();
            return "redirect:/signup";
        }
    }

    // ----------------- AUTHORIZATION -------------------------------------

    // -------------Check Login-----------------------
    // @RequestMapping(value = "/checklogin", method = {RequestMethod.GET,
    // RequestMethod.POST})
    @GetMapping("/checklogin")
    public String checkLogin( @ModelAttribute("userLogin") LoginForm loginForm, HttpSession session) {
        String email = loginForm.getEmail();
        String password = loginForm.getPassword();

        try {
            // 1. Check if the user exists in the database
            User user = userService.findUserByEmail(email);

            if (user!=null) {
                // 2. Check if the password is correct
                // Use a secure method to compare passwords (assuming passwords are hashed)
                if (passwordEncoder.matches(password, user.getPassword())) {
                    // Login successful, redirect to the dashboard
                    Message message = Message.builder()
                            .content("Login Successfully")
                            .type(MessageType.green)
                            .build();

                    session.setAttribute("message", message);
                    session.setAttribute("user", user);
                    return new String("/user/dashboard");
                } else {
                    // Password is incorrect, display an error message
                    Message message = Message.builder()
                            .content("Invalid Password")
                            .type(MessageType.red)
                            .build();

                    session.setAttribute("message", message);
                    return new String("login");
                }
            } else {
                // User does not exist, display an error message
                Message message = Message.builder()
                        .content("Invalid User, Please try again.")
                        .type(MessageType.red)
                        .build();

                session.setAttribute("message", message);
                return "redirect:/login";
            }
        } catch (Exception e) {
            // Handle exception and display an error message
            Message message = Message.builder()
                    .content("Invalid User, Please try again. Error: " + e.getMessage())
                    .type(MessageType.red)
                    .build();

            session.setAttribute("message", message);
            
            return "redirect:/login";
        }
    }

    // Contact us form BACKEND ------------- form submit here-------
    @PostMapping("/submit-contact-form")
    public String submitContactform(@ModelAttribute("contactform") ContactForm contactForm) {
        System.out.println(contactForm);
        System.out.println("Form Submit");
        return "redirect:/thanks";
    }
}
