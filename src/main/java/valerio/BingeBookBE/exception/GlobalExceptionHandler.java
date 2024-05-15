package valerio.BingeBookBE.exception;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import valerio.BingeBookBE.utils.ResponseEntityCustom;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleInternalError(Exception ex, WebRequest request) {
        return ResponseEntityCustom.responseError(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException ex, WebRequest request) {
        return ResponseEntityCustom.responseError(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        return ResponseEntityCustom.responseError(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<?> handleNullPointerException(NullPointerException ex, WebRequest request) {
        return ResponseEntityCustom.responseError(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
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
