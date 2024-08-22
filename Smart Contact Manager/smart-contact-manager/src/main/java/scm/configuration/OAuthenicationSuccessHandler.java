package scm.configuration;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import scm.entity.Providers;
import scm.entity.User;
import scm.helper.AppConstants;
import scm.service.UserService;

@Component
public class OAuthenicationSuccessHandler implements AuthenticationSuccessHandler {

    Logger logger = LoggerFactory.getLogger(OAuthenicationSuccessHandler.class);

    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication)
            throws IOException, ServletException {

        logger.info("OAuthenicationSuccessHandler");

        var OAuth2AccessToken = (OAuth2AccessToken) authentication;

        DefaultOAuth2User oAuth2User = (DefaultOAuth2User) authentication.getPrincipal();
        DefaultOAuth2User oAuth2User = (DefaultOAuth2User)
        authentication.getPrincipal();

        String email = oAuth2User.getAttribute("email");

        User user = new User();
        user.setFirstName(oAuth2User.getAttribute("given_name") != null ?
        oAuth2User.getAttribute("given_name") : "");
        user.setLastName(oAuth2User.getAttribute("family_name") != null ?
        oAuth2User.getAttribute("family_name") : "");
        user.setProfilePhoto(oAuth2User.getAttribute("picture") != null ?
        oAuth2User.getAttribute("picture") : "");
        user.setEmail(email);
        user.setProviderUserId(oAuth2User.getAttribute("sub") != null ?
        oAuth2User.getAttribute("sub") : "");
        user.setEmailVerified(true);
        user.setProviders(Providers.GOOGLE);
        user.setAbout("This account is created by Google");
        user.setRoleList(List.of(AppConstants.ROLE_USER));

        System.out.println(user);

        try {
        // if use is logged first time so then save the user id database
        if (!userService.isEmailExist(email)) {

        userService.saveUser(user);
        }

        } catch (Exception e) {
        // TODO: handle exception
        System.out.println(e);
        e.printStackTrace();
        }
        // Redirect to the user's profile page after successful authentication
        new DefaultRedirectStrategy().sendRedirect(request, response, "/user/profile");
    }
}
