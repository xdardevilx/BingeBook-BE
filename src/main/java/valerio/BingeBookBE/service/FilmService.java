package valerio.BingeBookBE.service;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import valerio.BingeBookBE.config.StringConfig;
import valerio.BingeBookBE.dto.FilmDTO;
import valerio.BingeBookBE.entity.Film;
import valerio.BingeBookBE.entity.Genre;
import valerio.BingeBookBE.entity.Tag;
import valerio.BingeBookBE.entity.User;
import valerio.BingeBookBE.repositories.FilmDAO;
import valerio.BingeBookBE.repositories.GenreDAO;
import valerio.BingeBookBE.repositories.TagDAO;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

@Service
public class FilmService {

    private final FilmDAO filmDAO;
    private final Cloudinary cloudinary;
    private final GenreDAO genreDAO;
    private final TagDAO tagDAO;

    @Autowired
    FilmService(FilmDAO filmDAO, Cloudinary cloudinary, GenreDAO genreDAO, TagDAO tagDAO) {
        this.filmDAO = filmDAO;
        this.cloudinary = cloudinary;
        this.genreDAO = genreDAO;
        this.tagDAO = tagDAO;
    }

    /// CREATE
    public Film createFilm(FilmDTO filmDTO, User user) {
        Film film = new Film();

        film.setTitle(filmDTO.title());

        Set<Genre> genres = new HashSet<>();
        for (BigInteger genreId : filmDTO.genreIds()) {
            Genre genre = genreDAO.findById(genreId)
                    .orElseThrow(() -> new IllegalArgumentException("Genre not found with ID: " + genreId));
            genres.add(genre);
        }

        film.setGenres(genres);

        Set<Tag> tags = new HashSet<>();
        for (BigInteger tagId : filmDTO.tagIds()) {
            Tag tag = tagDAO.findById(tagId)
                    .orElseThrow(() -> new IllegalArgumentException("Tag not found with ID: " + tagId));
            tags.add(tag);
        }

        film.setTags(tags);

        film.setPosterUrl(cloudinary.url().generate(filmDTO.posterUrl()));
        film.setUserRef(user);

        return filmDAO.save(film);
    }

    /// READ
    public Page<Film> getListFilms(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.filmDAO.findAll(pageable);
    }

    /// UPDATE
    public Film updateFilm(BigInteger idFilm, FilmDTO filmDTO, User user) {
        Film film = filmDAO.findById(idFilm)
                .orElseThrow(() -> new IllegalArgumentException(StringConfig.errorNotFoundRole + ": " + idFilm));

        film.setTitle(filmDTO.title());

        Set<Genre> genres = new HashSet<>();
        for (BigInteger genreId : filmDTO.genreIds()) {
            Genre genre = genreDAO.findById(genreId)
                    .orElseThrow(() -> new IllegalArgumentException("Genre not found with ID: " + genreId));
            genres.add(genre);
        }

        film.setGenres(genres);

        Set<Tag> tags = new HashSet<>();
        for (BigInteger tagId : filmDTO.tagIds()) {
            Tag tag = tagDAO.findById(tagId)
                    .orElseThrow(() -> new IllegalArgumentException("Tag not found with ID: " + tagId));
            tags.add(tag);
        }

        film.setTags(tags);

        film.setPosterUrl(cloudinary.url().generate(filmDTO.posterUrl()));
        film.setUserRef(user);
        return filmDAO.save(film);
    }

    /// DELETE
    public void deleteFilm(BigInteger idFilm) {
        filmDAO.deleteById(idFilm);
    }

}
