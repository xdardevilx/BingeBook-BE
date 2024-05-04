package valerio.BingeBookBE.dto;

import jakarta.validation.constraints.NotEmpty;
import valerio.BingeBookBE.config.StringConfig;

public record PersonalDataDTO(
        @NotEmpty(message = StringConfig.nameMessageError)
        String name,
        @NotEmpty(message = StringConfig.surnameMessageError)
        String surname
) {
}
