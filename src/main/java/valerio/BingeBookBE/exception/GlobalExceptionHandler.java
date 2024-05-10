package valerio.BingeBookBE.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import valerio.BingeBookBE.dto.ResponseDTO;

import com.fasterxml.jackson.databind.ObjectMapper;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final ObjectMapper objectMapper;

    public GlobalExceptionHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleInternalError(Exception ex, WebRequest request) {
        // Log the exception or perform any other necessary actions
        String errorMessage = ex.getMessage();
        // Create custom error response object
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), errorMessage);
        // Serialize error response object to JSON
        try {
            String jsonError = objectMapper.writeValueAsString(responseDTO);
            // Set JSON content type
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            // Return JSON response with error message
            return new ResponseEntity<>(jsonError, headers, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            // If serialization fails, return a generic error response
            return new ResponseEntity<>("{\"error\": \"Internal Server Error\"}", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException ex, WebRequest request) {
        // Log the exception or perform any other necessary actions
        String errorMessage = ex.getMessage();
        // Create custom error response object
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), errorMessage);
        // Serialize error response object to JSON
        try {
            String jsonError = objectMapper.writeValueAsString(responseDTO);
            // Set JSON content type
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            // Return JSON response with error message
            return new ResponseEntity<>(jsonError, headers, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            // If serialization fails, return a generic error response
            return new ResponseEntity<>("{\"error\": \"Internal Server Error\"}", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // @ExceptionHandler(NullPointerException.class)
    // public ResponseEntity<Object> handleNullPointerException(NullPointerException
    // ex, WebRequest request) {
    // // Log the exception or perform any other necessary actions
    // String errorMessage = ex.getMessage();

    // return handleExceptionInternal(ex, errorMessage, new HttpHeaders(),
    // HttpStatus.INTERNAL_SERVER_ERROR, request);
    // }

}
