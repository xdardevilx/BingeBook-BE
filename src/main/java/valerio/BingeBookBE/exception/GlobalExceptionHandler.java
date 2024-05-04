//package valerio.BingeBookBE.exception;
//
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//@ControllerAdvice
//public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
//
//    @Override
//    protected ResponseEntity<?> handleExceptionInternal(Exception ex, Object body, HttpStatus status, HttpHeaders headers, WebRequest request) {
//        // Log the exception
//        ex.printStackTrace();
//
//        // Customize the error response
//        String errorMessage = "An error occurred. Please try again later.";
//        return ResponseEntity.status(status).body(errorMessage);
//    }
//}
