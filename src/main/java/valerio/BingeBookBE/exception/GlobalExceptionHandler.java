package valerio.BingeBookBE.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleInternalError(Exception ex, WebRequest request) {
        // Log the exception or perform any other necessary actions
        String errorMessage = ex.getMessage();
        return handleExceptionInternal(ex, errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> handleNullPointerException(NullPointerException ex, WebRequest request) {
        // Log the exception or perform any other necessary actions
        String errorMessage = ex.getMessage();
        
        return handleExceptionInternal(ex, errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    // @Override
    // protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    //     // Log the exception or perform any other necessary actions
    //     Map<String, List<String>> body = new HashMap<>();
        
    //     List<String> errors = ex.getBindingResult()
    //         .getFieldErrors()
    //         .stream()
    //         .map(DefaultMessageSourceResolvable::getDefaultMessage)
    //         .collect(Collectors.toList());
        
    //     body.put("errors", errors);
        
    //     return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    // }
}
