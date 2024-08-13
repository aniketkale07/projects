package scm.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import scm.entity.User;
import scm.form.ContactForm;
import scm.form.LoginForm;
import scm.form.UserRegistration;
import scm.helper.Message;
import scm.helper.MessageType;
import scm.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@org.springframework.stereotype.Controller
public class Controller {

    private static final Logger logger = LoggerFactory.getLogger(Controller.class);


    @Autowired(required = true)
    private UserService userService;


    // private Logger logger=Logger.getLogger(getClass().getName());
    // -------------------------- Model -------------------------------

    @GetMapping("/home")
    public String userLogin(Model model) {
        logger.info("THis is User Home");
        return "home";
    }

    // Create a new user in web
    @GetMapping("/signup")
    public String userSignup(Model model) {

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
    public String newUser(@ModelAttribute("userReg") UserRegistration userReg, HttpSession session) {

        // TODO: process POST request
        // Processing Before Saving the user to database
        // Check the User is alredy availble in DB or not
        // if user is already availble in DB. then --> Invalid user
        // Otherwise save in DB and Redirect the user to login page
        // Add Encryption to password
        // Create new User
        User user = new User();
        String email = userReg.getEmail().strip();

        // Retrive user from DB
        User dbUser = userService.findUserByEmail(email);

        if (dbUser != null) {

            //---------------With Builder------------------
            Message message =Message.builder().content("Invalid Email").type(MessageType.red).build();
            session.setAttribute("message", message);
            
            // message Type
            // MessageType messageType = MessageType.red;
            // if (messageType != null) {
            //     Message message = new Message("Duplicate Email..", messageType);
            //     session.setAttribute("message", message);
            //     // RedirectAttributes.addFlashAttribute("message",message);
            // }

            System.out.println("Duplicate User Email..");
        } else {
            String encryptedPasswd = userReg.getPassword();

            user.setFirstName(userReg.getFName());
            user.setMiddleName(userReg.getMName());
            user.setLastName(userReg.getLName());

            user.setAbout(userReg.getAbout());
            user.setEmail(userReg.getEmail());
            user.setPassword(encryptedPasswd);
            user.setContact1(userReg.getContact1());
            user.setContact2(userReg.getContact2());
            user.setProfilePhoto(userReg.getProfilePhoto());

            userService.saveUser(user);

            //---------------Successs  Builder------------------
            Message message =Message.builder().content("Registration Successfully ").type(MessageType.green).build();
            session.setAttribute("message", message);
            System.out.println("User Registration Successfully");
            
        }
        return "redirect:/login";
    }

    // ----------------- AUTHORIZATION -------------------------------------

    // -------------Check Login-----------------------
    @GetMapping("/checklogin")
public String checkLogin(@Valid @ModelAttribute("userLogin") LoginForm loginForm, BindingResult bindingResult, HttpSession session) {
    if (bindingResult.hasErrors()) {
        Message message = Message.builder().content("Invalid Email ").type(MessageType.red).build();
        session.setAttribute("message", message);
        return "redirect:/login";
    } else {
        String email = loginForm.getEmail();
        String password = loginForm.getPassword();

        try {
            User user = userService.findUserByEmail(email);
            if (user == null) {
                Message message = Message.builder().content("Invalid Email ").type(MessageType.red).build();
                session.setAttribute("message", message);
                return "redirect:/login"; // return redirect instead of setting attribute and doing nothing
            } else if (!user.getPassword().equals(password)) { // add password validation
                Message message = Message.builder().content("Invalid Password ").type(MessageType.red).build();
                session.setAttribute("message", message);
                return "redirect:/login";
            } else {
                user.setLogin(true);
                session.setAttribute("user", user);
                return "redirect:/home";
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
            return "redirect:/login"; // return redirect instead of doing nothing
        }
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
