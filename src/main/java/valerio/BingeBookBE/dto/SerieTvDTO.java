package valerio.BingeBookBE.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import valerio.BingeBookBE.config.StringConfig;
import valerio.BingeBookBE.service.GenreServiceImpl.GenreIdsProvider;
import valerio.BingeBookBE.service.TagServiceImpl.TagsIdsProvider;

import java.util.Set;

public record SerieTvDTO(
                @NotEmpty(message = StringConfig.titleMessageError) String title,
                @NotNull(message = StringConfig.lastEpisodeViewed) int lastEpisodeViewed,
                @NotNull(message = StringConfig.lastEpisodeViewedSeason) int lastEpisodeViewedSeason,
                String posterUrl,
                @Override Set<Long> genreIds,
                @Override Set<Long> tagIds

) implements GenreIdsProvider, TagsIdsProvider {

}
