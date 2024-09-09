package scm.configuration;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.nimbusds.oauth2.sdk.auth.JWTAuthentication;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import scm.entity.Providers;
import scm.entity.User;
import scm.helper.AppConstants;
import scm.service.SecurityCustomUserDetailsService;
import scm.service.UserService;

@Component
public class OAuthenicationSuccessLoginHandler implements AuthenticationSuccessHandler {

    Logger logger = LoggerFactory.getLogger(OAuthenicationSuccessLoginHandler.class);

    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication)
            throws IOException, ServletException {

        logger.info("------------------OAuthenicationSuccessHandler-------------------");


        // --------------------the code is not working -------------------------
      // For the O2AuthUser
      if(authentication instanceof UsernamePasswordAuthenticationToken){
        UsernamePasswordAuthenticationToken token =(UsernamePasswordAuthenticationToken) authentication;
        UserDetails userDetails = (UserDetails)token.getPrincipal();

        String email= userDetails.getUsername();
        User user2 =userService.findUserByEmail(email);
        if(user2!=null){
            new DefaultRedirectStrategy().sendRedirect(request, response, "/user/dashboard");
        }
    }
     
      // --------------------the code is not working above -------------------------


        // Cast the authentication object to OAuth2AuthenticationToken
        OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;

        // Get the authorized client registration ID from the OAuth2AuthenticationToken
        String authorizedClientRegistrationId = oAuth2AuthenticationToken.getAuthorizedClientRegistrationId();

        // Print the authorized client registration ID to the console
        System.out.println("Here is the Authorize client registration id  :"+authorizedClientRegistrationId);

        DefaultOAuth2User oAuth2User = (DefaultOAuth2User) authentication.getPrincipal();

        System.out.println("Attributes:");

        // Print Authentication User getting from the at the time of logged

        System.out.println("Start of attributes.");
        
        oAuth2User.getAttributes().forEach((key, value) -> {
        System.out.println(key + " : " + value);
        });

        System.out.println("End of attributes.");

        User user = new User();

        user.setRoleList(List.of(AppConstants.ROLE_USER));

        // ---------------------Check the Token and apply condition for user to redirect
        // and save
        // the user details in the database---------------------------

        if (authorizedClientRegistrationId.equalsIgnoreCase("google")) {
            // Set Google Authentication Code

            String email = oAuth2User.getAttribute("email");

            user.setFirstName(
                    oAuth2User.getAttribute("given_name") != null ? oAuth2User.getAttribute("given_name") : "");
            user.setLastName(
                    oAuth2User.getAttribute("family_name") != null ? oAuth2User.getAttribute("family_name") : "");
            user.setProfilePhoto(
                    oAuth2User.getAttribute("picture") != null ? oAuth2User.getAttribute("picture") : "");
            user.setEmail(email);
            user.setProviderUserId(
                    oAuth2User.getAttribute("sub") != null ? oAuth2User.getAttribute("sub") : "");
            user.setEmailVerified(true);
            user.setProviders(Providers.GOOGLE);
            user.setAbout("This account is created by Google");

            System.out.println(user);

            // if use is logged first time so then save the user id database
            if (!userService.isEmailExist(email)) {

                userService.saveUser(user);
            }

           
        } 
   // --------------GITHUB ----------------      
        else if (authorizedClientRegistrationId.equalsIgnoreCase("github")) {
            String email = oAuth2User.getAttribute("email") != null ? 
            oAuth2User.getAttribute("email").toString() :
            (oAuth2User.getAttribute("login").toString() + "@github.com");
            
            // Set Github Authentication Code
            if (!userService.isEmailExist(email)) {
               
            //    SET eMAIL
            user.setEmail(email);
                // set Provier
                user.setProviders(Providers.GITHUB);
                // Set Provider ID
                String providerId = oAuth2User.getAttribute("id") != null ? oAuth2User.getAttribute("id").toString()
                        : "";
                user.setProviderUserId(providerId);

                // Set Profile pic
                String avatarUrl = oAuth2User.getAttribute("avatar_url");
                if (avatarUrl != null) {
                    user.setProfilePhoto(avatarUrl);
                }
                // Set User bio
                user.setAbout(oAuth2User.getAttribute("bio") != null ? oAuth2User.getAttribute("bio").toString() : "");
                // get user full name
                String fullName = oAuth2User.getAttribute("name") != null ? oAuth2User.getAttribute("name").toString()
                        : "";

                if (!fullName.isBlank()) {
                    String[] nameParts = fullName.split(" ");
                    if (nameParts.length > 0) {
                        user.setFirstName(nameParts[0]); // First name
                    }
                    if (nameParts.length > 1) {
                        user.setLastName(nameParts[nameParts.length - 1]); // Last name
                    }
                }
                user.setAbout(oAuth2User.getAttribute("bio") != null ? oAuth2User.getAttribute("bio").toString() : "");

              
                userService.saveUser(user);
            }

        } else{
            // for non OA2uthUser 
            UserDetails userDetails = (UserDetails)authentication.getPrincipal();            
            String email =userDetails.getUsername();

            User user2 = userService.findUserByEmail(email);
        }

       

        // Redirect to the user's profile page after successful authentication
        new DefaultRedirectStrategy().sendRedirect(request, response, "/user/dashboard");
    }
}
