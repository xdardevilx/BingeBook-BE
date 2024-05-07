package valerio.BingeBookBE.exception;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

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

    private void handleJwtException(Exception ex, HttpServletResponse response) throws IOException {
        if (ex instanceof ExpiredJwtException || ex instanceof UnsupportedJwtException || ex instanceof MalformedJwtException || ex instanceof JwtException) {
            // Token related exceptions
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Problems with token! Please login again!");
        } else {
            // Other exceptions
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal Server Error");
        }
    }
}
