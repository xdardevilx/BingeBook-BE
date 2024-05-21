package valerio.BingeBookBE.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import org.springframework.lang.NonNull;
import valerio.BingeBookBE.exception.ErrorInterceptor;
import valerio.BingeBookBE.security.AuthTokenInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    private final ErrorInterceptor errorInterceptor;
    private final AuthTokenInterceptor authTokenInterceptor;

    @Autowired
    public WebConfig(ErrorInterceptor errorInterceptor, AuthTokenInterceptor authTokenInterceptor) {
        this.errorInterceptor = errorInterceptor;
        this.authTokenInterceptor = authTokenInterceptor;
    }

    @Override
    public void addInterceptors(@NonNull InterceptorRegistry registry) {
        // Add ErrorInterceptor for handling exceptions globally
        registry.addInterceptor(errorInterceptor).addPathPatterns("/**");
        
        // Add AuthTokenInterceptor for handling authorization token globally
        registry.addInterceptor(authTokenInterceptor)
        .excludePathPatterns("/auth/**");
    }
}
