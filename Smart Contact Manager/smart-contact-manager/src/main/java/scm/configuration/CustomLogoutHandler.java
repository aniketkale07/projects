package scm.configuration;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class CustomLogoutHandler implements LogoutHandler {

    @Override
    public void logout(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) {

        HttpSession session = request.getSession(false);

        // Invalidate session if it exists
        if (session != null) {
            session.invalidate();
        }

        // Clear the JSESSIONID cookie
        Cookie cookie = new Cookie("JSESSIONID", null);
        cookie.setPath("/"); // Clear the cookie for the entire application
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0); // Immediately expire the cookie
        response.addCookie(cookie);

        // Clear security context
        SecurityContextHolder.clearContext();

        // Add headers to prevent caching
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
    }
}
