package valerio.BingeBookBE.dto;

import jakarta.validation.constraints.NotEmpty;
import valerio.BingeBookBE.config.StringConfig;
import valerio.BingeBookBE.service.GenreServiceImpl.GenreIdsProvider;
import valerio.BingeBookBE.service.TagServiceImpl.TagsIdsProvider;

import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

public record FilmDTO(
        @NotEmpty(message = StringConfig.titleMessageError) String title,
        MultipartFile posterUrl,
        @Override Set<Long> genreIds,
        @Override Set<Long> tagIds) implements GenreIdsProvider, TagsIdsProvider {
}
