package valerio.BingeBookBE.dto;

import valerio.BingeBookBE.entity.User;

public record UserLoginResponseDTO(
        boolean success,
        User user,
        String token) {
}
