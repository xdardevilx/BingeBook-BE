package valerio.BingeBookBE.dto;

import jakarta.validation.constraints.NotEmpty;
import valerio.BingeBookBE.config.StringConfig;

import java.math.BigInteger;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

public record FilmDTO(
                @NotEmpty(message = StringConfig.titleMessageError) String title,
        MultipartFile posterUrl,
                Set<BigInteger> genreIds,
                Set<BigInteger> tagIds) {

}
