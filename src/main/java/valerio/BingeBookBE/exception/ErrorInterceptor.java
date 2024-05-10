package valerio.BingeBookBE.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class ErrorInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // Add pre-processing logic here
        int statusCode = response.getStatus();
        switch (statusCode) {
            case HttpServletResponse.SC_INTERNAL_SERVER_ERROR:
                // Handle 500 Internal Server Error
                // Log the error, send an email notification, etc.
                System.out.println("Internal Server Error occurred");
                break;
            case HttpServletResponse.SC_NOT_FOUND:
                // Handle 404 Not Found
                // Redirect to a custom error page, log the error, etc.
                System.out.println("Resource not found: " + request.getRequestURI());
                break;
            case HttpServletResponse.SC_UNAUTHORIZED:
                // Handle 401 Unauthorized
                // Redirect to login page, send error response, etc.
                System.out.println("Unauthorized access: " + request.getRequestURI());
                break;
            default:
                // Handle other status codes if needed
                break;
        }
        return true; // Return true to continue processing the request
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        // Add post-processing logic here
        int statusCode = response.getStatus();
        switch (statusCode) {
            case HttpServletResponse.SC_INTERNAL_SERVER_ERROR:
                // Handle 500 Internal Server Error
                // Log the error, send an email notification, etc.
                System.out.println("Internal Server Error occurred");
                break;
            case HttpServletResponse.SC_NOT_FOUND:
                // Handle 404 Not Found
                // Redirect to a custom error page, log the error, etc.
                System.out.println("Resource not found: " + request.getRequestURI());
                break;
            case HttpServletResponse.SC_UNAUTHORIZED:
                // Handle 401 Unauthorized
                // Redirect to login page, send error response, etc.
                System.out.println("Unauthorized access: " + request.getRequestURI());
                break;
            default:
                // Handle other status codes if needed
                break;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        int statusCode = response.getStatus();
        switch (statusCode) {
            case HttpServletResponse.SC_INTERNAL_SERVER_ERROR:
                // Handle 500 Internal Server Error
                // Log the error, send an email notification, etc.
                System.out.println("Internal Server Error occurred");
                logErrorDetails(request, ex);
                break;
            case HttpServletResponse.SC_NOT_FOUND:
                // Handle 404 Not Found
                // Redirect to a custom error page, log the error, etc.
                System.out.println("Resource not found: " + request.getRequestURI());
                break;
            case HttpServletResponse.SC_UNAUTHORIZED:
                // Handle 401 Unauthorized
                // Redirect to login page, send error response, etc.
                System.out.println("Unauthorized access: " + request.getRequestURI());
                break;
            default:
                // Handle other status codes if needed
                break;
        }
    }

    private void logErrorDetails(HttpServletRequest request, Exception ex) {
        // Log error details
        System.out.println("Error details:");
        if (request != null) {
            System.out.println("Request URL: " + request.getRequestURL());
        }
        if(ex != null) {
            System.out.println("Exception message: " + ex.getMessage());
            // Log stack trace if needed
            ex.printStackTrace();
        }
    }
}
