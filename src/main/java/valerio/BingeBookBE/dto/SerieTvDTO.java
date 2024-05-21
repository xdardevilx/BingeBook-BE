package valerio.BingeBookBE.dto;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;
import valerio.BingeBookBE.config.StringConfig;
import valerio.BingeBookBE.service.GenreServiceImpl.GenreIdsProvider;
import valerio.BingeBookBE.service.TagServiceImpl.TagsIdsProvider;

import java.util.Set;

public record SerieTvDTO(
        @NotEmpty(message = StringConfig.titleMessageError) String title,
        @NotEmpty(message = StringConfig.lastEpisodeViewed) int lastEpisodeViewed,
        @NotEmpty(message = StringConfig.lastEpisodeViewedSeason) int lastEpisodeViewedSeason,
        MultipartFile posterUrl,
        @Override Set<Long> genreIds,
        @Override Set<Long> tagIds

) implements GenreIdsProvider, TagsIdsProvider  {

}
