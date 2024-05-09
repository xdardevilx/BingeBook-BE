package valerio.BingeBookBE.dto;

public record UserPersonalDataRoleDTO(
        UserDTO userDTO,
        PersonalDataDTO personalDataDTO,
        RoleDTO roleDTO) {
}
