package valerio.BingeBookBE.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import valerio.BingeBookBE.config.StringConfig;
import valerio.BingeBookBE.dto.FilmDTO;
import valerio.BingeBookBE.dto.GenreDTO;
import valerio.BingeBookBE.dto.PageableDTO;
import valerio.BingeBookBE.entity.Film;
import valerio.BingeBookBE.entity.Genre;
import valerio.BingeBookBE.repositories.GenreDAO;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

@Service
public class GenreService {

    private final GenreDAO genreDAO;

    @Autowired
    GenreService(GenreDAO genreDAO) {
        this.genreDAO = genreDAO;
    }

    public Genre saveGenre(GenreDTO genreDto) {
        Genre genre = new Genre();
        genre.setName(genreDto.name().toLowerCase());

        return genreDAO.save(genre);
    }

    public Genre getGenreById(BigInteger id) {
        return genreDAO.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(StringConfig.errorNotFoundGenre + ": " + id));
    }

    public PageableDTO<Genre> getListGenres(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Genre> genrePage = this.genreDAO.findAll(pageable);
        return new PageableDTO<>(
                genrePage.getNumber(),
                genrePage.getSize(),
                genrePage.getTotalPages(),
                genrePage.getTotalElements(),
                sortBy,
                genrePage.getContent());
    }

    public Genre updateGenre(GenreDTO genreDto, BigInteger id) {
        Genre genre = getGenreById(id);

        if (genre == null) {
            throw new IllegalArgumentException(StringConfig.errorNotFoundGenre + ": " + id);
        }

        genre.setName(genreDto.name().toLowerCase());

        return genreDAO.save(genre);
    }

    public void deleteGenre(BigInteger id) {

        if (getGenreById(id) != null) {
            genreDAO.deleteById(id);
        } else {
            throw new IllegalArgumentException(StringConfig.errorNotFoundGenre + ": " + id);
        }
    }

    public interface GenreIdsProvider {
        Set<BigInteger> genreIds();
    }

    public <T extends GenreIdsProvider> Set<Genre>  hashSetGenres(T t) {
        Set<Genre> genres = new HashSet<>();
        for (BigInteger genreId : t.genreIds()) {
            Genre genre = getGenreById(genreId);
            genres.add(genre);
        }
        return genres;
    }
}
