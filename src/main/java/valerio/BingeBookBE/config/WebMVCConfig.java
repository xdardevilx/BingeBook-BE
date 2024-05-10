package valerio.BingeBookBE.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import valerio.BingeBookBE.exception.ErrorInterceptor;

@Configuration
public class WebMVCConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("Adding ErrorInterceptor");
        registry.addInterceptor(new ErrorInterceptor())
                .addPathPatterns("/**");
    }
}
