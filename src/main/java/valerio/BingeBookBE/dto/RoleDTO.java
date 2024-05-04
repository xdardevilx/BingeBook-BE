package valerio.BingeBookBE.dto;

import jakarta.validation.constraints.NotEmpty;
import valerio.BingeBookBE.config.StringConfig;

public record RoleDTO(
        @NotEmpty(message = StringConfig.roleMessageError)
        String name
) {
}
