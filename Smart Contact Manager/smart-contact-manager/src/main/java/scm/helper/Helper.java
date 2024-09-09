package scm.helper;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class Helper {

    // this method help to you find logged user email
    public static String getLoggedUserEmail(Authentication authentication) {

        if (authentication == null) {
            return "Authentication is null";
        }

        OAuth2AuthenticationToken oAuth2Token = (OAuth2AuthenticationToken) authentication;
        var providerId = oAuth2Token.getAuthorizedClientRegistrationId();
        
        OAuth2User oAuth2User = (OAuth2User) oAuth2Token.getPrincipal();
        System.out.println("OAuth2 Client ID: " + providerId);

        String username="";


        if (authentication instanceof OAuth2AuthenticationToken) {
           
            if (providerId.equalsIgnoreCase("google")) {
              
                 username = oAuth2User.getAttribute("email");
                System.out.println("OAuth2 User Email: " + username);

            } 
            else if(providerId.equalsIgnoreCase("github")){
                 username = oAuth2User.getAttribute("email") != null ? 
            oAuth2User.getAttribute("email").toString() :
            (oAuth2User.getAttribute("login").toString() + "@github.com");
               System.out.println("OAuth2 User Email: " + username);
            }
            return username;
        } else {
           
            // ------------------UNAUTHORIZE CODE IS NOT WORKING-----------------------
            // if(authentication.getPrincipal() instanceof UserDetails){
            //     UserDetails userDetails =(UserDetails) authentication.getPrincipal();
            //     username=userDetails.getUsername();
            // }else{
                username =authentication.getName();
            // }
            // ----------NOTE : UnAutorize user is not working
            // System.out.println(userDetails.getUsername());
          System.out.println("The value of Username is" + username);
            return username;
        }
    }
    
}
