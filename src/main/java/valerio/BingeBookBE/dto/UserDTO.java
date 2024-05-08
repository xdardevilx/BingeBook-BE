package valerio.BingeBookBE.dto;

import jakarta.validation.constraints.NotEmpty;
import valerio.BingeBookBE.config.StringConfig;
import valerio.BingeBookBE.entity.User;

import java.math.BigInteger;
import java.util.Set;

public record UserDTO(
        @NotEmpty(message = StringConfig.usernameMessageError)
        String username,

        @NotEmpty(message = StringConfig.emailMessageError)
        String email,

        @NotEmpty(message = StringConfig.passwordMessageError)
        String password,

        @NotEmpty(message = StringConfig.roleMessageError)
        BigInteger roleId,

        Set<BigInteger> serieTvIds,
        Set<BigInteger> filmIds,
        Set<BigInteger> tagIds
) {
}
