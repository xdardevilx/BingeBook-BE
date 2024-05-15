package valerio.BingeBookBE.utils;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import valerio.BingeBookBE.dto.ResponseDTO;

public class ResponseEntityCustom {
    public static final <T> ResponseEntity<?> responseSuccess(T data, HttpStatusCode httpStatusCode) {
        return new ResponseEntity<>(new ResponseDTO<T>("success", httpStatusCode.toString().toLowerCase(), data),
                httpStatusCode);
    }

    public static final ResponseEntity<?> responseError(String message, HttpStatusCode httpStatusCode) {
        return new ResponseEntity<>(new ResponseDTO<String>("error", httpStatusCode.toString().toLowerCase(), message),
                httpStatusCode);
    }
}
