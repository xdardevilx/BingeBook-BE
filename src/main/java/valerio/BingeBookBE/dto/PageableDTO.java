package valerio.BingeBookBE.dto;

import java.util.List;

public record PageableDTO<T>(
        int page,
        int size,
        int totalPages,
                long totalElements,
        String sortBy,
        List<T> items) {
}
