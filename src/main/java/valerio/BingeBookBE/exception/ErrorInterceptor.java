package valerio.BingeBookBE.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class ErrorInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Add pre-processing logic here
        return true; // Return true to continue processing the request
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // Add post-processing logic here
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if (response.getStatus() == HttpStatus.BAD_REQUEST.value() || response.getStatus() == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            // Handle errors for status codes 400 and 500
            // Log the error
            System.err.println("Error " + response.getStatus() + ": " + HttpStatus.valueOf(response.getStatus()).getReasonPhrase());
            // Optionally, perform additional error handling tasks
        }
    }
}
