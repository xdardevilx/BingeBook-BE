package valerio.BingeBookBE.dto;

import io.micrometer.common.lang.Nullable;

public record ResponseDTO(
                @Nullable int code,
                @Nullable String message) {
}
