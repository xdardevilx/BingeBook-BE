package valerio.BingeBookBE.config;


import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class ServerConfig {
    @Bean
    public Cloudinary uploaderImg(@Value("${cloudinary.cloudName}") String cloudName,
                                  @Value("${cloudinary.apiKey}") String apiKey,
                                  @Value("${cloudinary.apiSecret}") String apiSecret) {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", cloudName);
        config.put("api_key", apiKey);
        config.put("api_secret", apiSecret);
        return new Cloudinary(config);


    }


}
