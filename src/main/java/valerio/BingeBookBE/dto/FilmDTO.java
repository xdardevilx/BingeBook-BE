package valerio.BingeBookBE.dto;

import jakarta.validation.constraints.NotEmpty;
import valerio.BingeBookBE.config.StringConfig;

import java.math.BigInteger;
import java.util.Set;

public record FilmDTO(
        @NotEmpty(message = StringConfig.titleMessageError)
        String title,

        Set<BigInteger> genreIds,
        Set<BigInteger> tagIds
) {

}
