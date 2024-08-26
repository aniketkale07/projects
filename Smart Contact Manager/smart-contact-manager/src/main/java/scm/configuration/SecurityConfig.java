package scm.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import scm.service.SecurityCustomUserDetailsService;

@Configuration
public class SecurityConfig {

    @Autowired(required = true)
    private SecurityCustomUserDetailsService UserDetailsService;
    
    @Autowired(required = true)
    private OAuthenicationSuccessLoginHandler oAuthenicationSuccessLoginHandler;
    // Authentication Provider
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(UserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    
    }
    // Password Encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    // Security Configuration specift the Routes
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
    
                HttpSecurity.formLogin(formLogin ->{
                    formLogin.loginPage("/login")
                            .loginProcessingUrl("/checklogin")
                            .successForwardUrl("/user/profile")
                            .failureForwardUrl("/login?error=true")
                            .usernameParameter("email")
                            .passwordParameter("password");

            // formLogin.failureHandler(new AuthenticationFailureHandler() {

            //     @Override
            //     public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            //             AuthenticationException exception) throws IOException, ServletException {
            //         // TODO Auto-generated method stub
            //         throw new UnsupportedOperationException("Unimplemented method 'onAuthenticationFailure'");
            //     }
                
            // });

            // formLogin.successHandler(new AuthenticationSuccessHandler() {

            //     @Override
            //     public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            //             Authentication authentication) throws IOException, ServletException {
            //         // TODO Auto-generated method stub
            //         throw new UnsupportedOperationException("Unimplemented method 'onAuthenticationSuccess'");
            //     }
                
            // });
    });
    
    // HttpSecurity.csrf(AbstractHttpConfigurer::disable);
    HttpSecurity.logout(logoutForm->{
        logoutForm.logoutUrl("/logout");
        logoutForm.invalidateHttpSession(true);
        logoutForm.deleteCookies("JSESSIONID");
        logoutForm.logoutSuccessUrl("/login?logout=true");
        // logoutForm.logoutSuccessHandler(null);
    });
    
    //OAuth Configuration for Google
    HttpSecurity.oauth2Login(oauth->{
        oauth.loginPage("/login");
        oauth.successHandler(oAuthenicationSuccessLoginHandler);
    });

    HttpSecurity.logout(logoutForm->{
        logoutForm.logoutUrl("/logout");
        
        // logoutForm.invalidateHttpSession(true);
        // logoutForm.deleteCookies("JSESSIONID");
        logoutForm.logoutSuccessUrl("/login?logout=true");
        // // logoutForm.logoutSuccessHandler(logoutHandler);
        // logoutForm.logoutSuccessHandler(null);
    });

        return HttpSecurity.build();   
    }

    // Password Encoder
  
}
