package valerio.BingeBookBE.dto;

import jakarta.validation.constraints.NotEmpty;
import valerio.BingeBookBE.config.StringConfig;

public record GenreDTO(
        @NotEmpty(message = StringConfig.genreMessageError)
        String name
) {
}
