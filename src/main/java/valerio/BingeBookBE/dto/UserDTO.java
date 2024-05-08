package valerio.BingeBookBE.dto;

import jakarta.validation.constraints.NotEmpty;
import valerio.BingeBookBE.config.StringConfig;

import java.math.BigInteger;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

public record UserDTO(
        @NotEmpty(message = StringConfig.usernameMessageError)
        String username,

        @NotEmpty(message = StringConfig.emailMessageError)
        String email,

        @NotEmpty(message = StringConfig.passwordMessageError)
        String password,

        @NotEmpty(message = StringConfig.roleMessageError)
                BigInteger roleId,
        
                MultipartFile profilePicture,

        Set<BigInteger> serieTvIds,
        Set<BigInteger> filmIds,
        Set<BigInteger> tagIds
) {
}
