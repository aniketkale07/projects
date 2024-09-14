package scm.configuration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Configuration
public class AppConfig {

    @Value("${cloudinary.cloud.name}")
    private String cloudName;

    @Value("${cloudinary.cloud.key}")
    private String apiKey;

    @Value("${cloudinary.cloud.secret}")
    private String apiSecret;

    @Bean
    public Cloudinary cloudinary() {
        
        return new Cloudinary(
            ObjectUtils.asMap(
                "cloud_name","de842ya0v",
                "api_key","371259622722197",
                "api_secret","jTe5q7644hoJXSClv3zu9jiVHow"
            )
        );
    }
}
