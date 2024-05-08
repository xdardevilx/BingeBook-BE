package valerio.BingeBookBE.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpsecurity) throws Exception {
        httpsecurity.formLogin(AbstractHttpConfigurer::disable);
        httpsecurity.csrf(AbstractHttpConfigurer::disable);
        httpsecurity.sessionManagement(http -> http.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        httpsecurity.authorizeHttpRequests(http -> http.requestMatchers("/**").permitAll());
        return httpsecurity.build();
    }

    @Bean
    PasswordEncoder getByCrypt() {
        return new BCryptPasswordEncoder(15);
    }
//
//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration config = new CorsConfiguration();
//
//        config.setAllowedOrigins(Arrays.asList("http://localhost:3002"));
//        config.setAllowedMethods(Arrays.asList("*"));
//        config.setAllowedHeaders(Arrays.asList("*"));
//        UrlBasedCorsConfigurationSource src = new UrlBasedCorsConfigurationSource();
//        src.registerCorsConfiguration("/**", config);
//
//        return src;
//    }
}
