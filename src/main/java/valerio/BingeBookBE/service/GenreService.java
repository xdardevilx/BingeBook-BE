package valerio.BingeBookBE.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import valerio.BingeBookBE.config.StringConfig;
import valerio.BingeBookBE.dto.GenreDTO;
import valerio.BingeBookBE.entity.Genre;
import valerio.BingeBookBE.repositories.GenreDAO;

import java.math.BigInteger;

@Service
public class GenreService {

    @Autowired
    private GenreDAO genreDAO;

    /// CREATE
    public Genre save(GenreDTO genreDto) {

        Genre genre = new Genre();
        genre.setName(genreDto.name().toLowerCase());

        return genreDAO.save(genre);
    }

    public Genre findById(BigInteger id) {
        return genreDAO.findById(id).orElseThrow(() -> new IllegalArgumentException(StringConfig.errorNotFoundGenre));
    }

    /// READ
    public Page<Genre> getListGenres(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.genreDAO.findAll(pageable);
    }

    /// UPDATE
    public Genre updateGenre(GenreDTO genreDto, BigInteger id) {
        Genre genre = genreDAO.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(StringConfig.errorNotFoundGenre));
        genre.setName(genreDto.name().toLowerCase());

        return genreDAO.save(genre);
    }

}
