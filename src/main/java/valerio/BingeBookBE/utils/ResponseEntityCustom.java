package valerio.BingeBookBE.utils;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import valerio.BingeBookBE.dto.response.ApiResponse;

public class ResponseEntityCustom {
    public static final <T> ResponseEntity<?> responseSuccess(T data, HttpStatusCode httpStatusCode) {
        return new ResponseEntity<>(new ApiResponse<T>(data, "success", httpStatusCode.toString().toLowerCase()),
                httpStatusCode);
    }

    public static final ResponseEntity<?> responseError(String message, HttpStatusCode httpStatusCode) {
        return new ResponseEntity<>(new ApiResponse<String>(message, "error", httpStatusCode.toString().toLowerCase()),
                httpStatusCode);
    }
}
