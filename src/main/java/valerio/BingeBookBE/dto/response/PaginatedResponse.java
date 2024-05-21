package valerio.BingeBookBE.dto.response;

import java.util.List;

public record PaginatedResponse<T>(
                List<T> items,
                int page,
                int pageSize,
                long totalPages,
                long totalItems,
                String sortBy) {
}
