package scm.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import scm.service.SecurityCustomUserDetailsService;

@Configuration
public class SecurityConfig {

    @Autowired(required = true)
    SecurityCustomUserDetailsService UserDetailsService;

    // Authentication Provider
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(UserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;

    }

    // Security Configuration
    @Bean
    public SecurityFilterChain securityFilterChain(
            org.springframework.security.config.annotation.web.builders.HttpSecurity HttpSecurity) throws Exception {
        HttpSecurity
                .authorizeHttpRequests(authorize -> {
                    // authorize.requestMatchers("/home","/login","/signup","/about","/contact").permitAll();
                    // IT authenticated request of user 
                    authorize.requestMatchers("/user/**").authenticated();
                    // it will permit all other request public 
                    authorize.anyRequest().permitAll();
                });

                HttpSecurity.formLogin(Customizer.withDefaults());
        return HttpSecurity.build();
    }

    // Password Encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
