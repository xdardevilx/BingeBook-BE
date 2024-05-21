package valerio.BingeBookBE.dto;

import jakarta.validation.constraints.NotEmpty;
import valerio.BingeBookBE.config.StringConfig;

import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

public record UserDTO(
                Long id,
                @NotEmpty(message = StringConfig.usernameMessageError) String username,

                @NotEmpty(message = StringConfig.emailMessageError) String email,

                @NotEmpty(message = StringConfig.passwordMessageError) String password,

                MultipartFile profilePicture,

                @NotEmpty(message = StringConfig.roleMessageError) Long roleId,

                Set<Long> serieTvIds,
                Set<Long> filmIds,
                Set<Long> tagIds) {
}
