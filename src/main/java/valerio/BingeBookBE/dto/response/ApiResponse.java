package valerio.BingeBookBE.dto.response;

public record ApiResponse<T>(
        T data,
        String message,
        String status) {
}
