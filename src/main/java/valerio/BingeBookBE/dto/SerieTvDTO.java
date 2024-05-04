package valerio.BingeBookBE.dto;

import jakarta.validation.constraints.NotEmpty;

import java.math.BigInteger;
import java.util.Set;

public record SerieTvDTO(
        @NotEmpty(message = "il titolo è obbligatorio")
        String title,
        @NotEmpty(message = "ultimo episodio visto è obbligatorio")
        int lastEpisodeViewed,
        @NotEmpty(message = "ultima stagione vista è obbligatoria")
        int lastEpisodeViewedSeason,

        Set<BigInteger> genreIds

) {

}
