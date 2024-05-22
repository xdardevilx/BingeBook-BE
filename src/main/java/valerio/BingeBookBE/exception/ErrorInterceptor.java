package valerio.BingeBookBE.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import valerio.BingeBookBE.config.StringConfig;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class ErrorInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
            @NonNull Object handler)
            throws Exception {
        System.out.println("preHandle method is calling");
        System.out.println("Request Method: " + request.getMethod());
        System.out.println("Request URI: " + request.getRequestURI());
        System.out.println("Request URL: " + request.getRequestURL());
        // Add pre-processing logic here
        // Get the BindingResult from the request attributes
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
                throw new RuntimeException(StringConfig.errorEmailAndPassword);
            default:
                // Handle other status codes if needed
                break;
        }
        return true; // Return true to continue processing the request
    }

    @Override
    public void postHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
            @NonNull Object handler,
            @Nullable ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle method is calling");
        System.out.println("Request Method: " + request.getMethod());
        System.out.println("Request URI: " + request.getRequestURI());
        System.out.println("Request URL: " + request.getRequestURL());

        // // Add post-processing logic here
        // int statusCode = response.getStatus();
        // switch (statusCode) {
        // case HttpServletResponse.SC_INTERNAL_SERVER_ERROR:
        // // Handle 500 Internal Server Error
        // // Log the error, send an email notification, etc.
        // System.out.println("Internal Server Error occurred");
        // break;
        // case HttpServletResponse.SC_NOT_FOUND:
        // // Handle 404 Not Found
        // // Redirect to a custom error page, log the error, etc.
        // System.out.println("Resource not found: " + request.getRequestURI());
        // break;
        // case HttpServletResponse.SC_UNAUTHORIZED:
        // // Handle 401 Unauthorized
        // // Redirect to login page, send error response, etc.
        // System.out.println("Unauthorized access: " + request.getRequestURI());
        // break;
        // default:
        // // Handle other status codes if needed
        // break;
        // }
    }

    @Override
    public void afterCompletion(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
            @NonNull Object handler, @Nullable Exception ex)
            throws Exception {

        System.out.println("afterCompletion method is calling");
        System.out.println("Request Method: " + request.getMethod());
        System.out.println("Request URI: " + request.getRequestURI());
        System.out.println("Request URL: " + request.getRequestURL());

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
        if (ex != null) {
            System.out.println("Exception message: " + ex.getMessage());
            // Log stack trace if needed
            ex.printStackTrace();
        }
    }
}
