package valerio.BingeBookBE.dto;

import jakarta.validation.constraints.NotEmpty;
import valerio.BingeBookBE.config.StringConfig;

public record TagDTO(
        @NotEmpty(message = StringConfig.tagMessageError)
        String name
) {

}
