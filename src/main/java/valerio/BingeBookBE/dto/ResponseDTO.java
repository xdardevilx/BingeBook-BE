package valerio.BingeBookBE.dto;

import io.micrometer.common.lang.Nullable;

public record ResponseDTO<T>(
        @Nullable String status,
        @Nullable String message,
        @Nullable T data) {
}
