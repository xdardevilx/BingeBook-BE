package valerio.BingeBookBE.dto;

import jakarta.validation.constraints.NotEmpty;
import valerio.BingeBookBE.config.StringConfig;

import java.math.BigInteger;
import java.util.Set;

public record SerieTvDTO(
        @NotEmpty(message = StringConfig.titleMessageError)
        String title,
        @NotEmpty(message = StringConfig.lastEpisodeViewed)
        int lastEpisodeViewed,
        @NotEmpty(message = StringConfig.lastEpisodeViewedSeason)
        int lastEpisodeViewedSeason,
        String posterUrl,
        Set<BigInteger> genreIds,
        Set<BigInteger> tagIds

) {

}
