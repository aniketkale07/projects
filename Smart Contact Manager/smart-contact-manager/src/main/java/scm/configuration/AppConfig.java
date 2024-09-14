package scm.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Configuration
public class AppConfig {
    
    @Value("${cloudinary.cloud.name}")
    private String cloudName;

    @Value("${cloudinary.api.key}")
    private String apiKey;
    @Value("${cloud.api.secret}")
    private String apiSecret;

    @Bean
    public Cloudinary cloudinary(){
        ObjectUtils.asMap();
        
        return new Cloudinary();

    }
}
