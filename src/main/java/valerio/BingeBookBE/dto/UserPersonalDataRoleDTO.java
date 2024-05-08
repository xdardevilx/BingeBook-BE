package valerio.BingeBookBE.dto;

import jakarta.validation.constraints.NotEmpty;

import valerio.BingeBookBE.config.StringConfig;

public record UserPersonalDataRoleDTO(
                @NotEmpty(message = StringConfig.userMessageError) UserDTO userDTO,
                @NotEmpty(message = StringConfig.personalDataMessageError) PersonalDataDTO personalDataDTO,
                RoleDTO roleDTO) {
}
