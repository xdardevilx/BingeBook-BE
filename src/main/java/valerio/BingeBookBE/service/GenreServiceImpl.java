package valerio.BingeBookBE.service;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import valerio.BingeBookBE.config.StringConfig;
import valerio.BingeBookBE.dto.GenreDTO;
import valerio.BingeBookBE.entity.Genre;
import valerio.BingeBookBE.repositories.GenreDAO;
import valerio.BingeBookBE.service.interfaces.GenreService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreDAO genreDAO;

    @Autowired
    GenreServiceImpl(GenreDAO genreDAO) {
        this.genreDAO = genreDAO;
    }

    @Override
    public Genre getGenreById(Long id) {
        return genreDAO.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(StringConfig.errorNotFoundGenre + ": " + id));
    }

    @Override
    public Genre getGenreByName(String name) {
        return genreDAO.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException(StringConfig.errorNotFoundGenre + ": " + name));
    }

    @Override
    public List<Genre> getAllGenres() {
        try {
            return genreDAO.findAll();
        } catch (Exception e) {
            throw new ServiceException("Failed to fetch all users", e);
        }
    }

    @Override
    public void createGenre(GenreDTO genreDto) {
        if (genreDAO.existsByName(genreDto.name().toLowerCase())) {
            throw new EntityNotFoundException(StringConfig.errorAlreadyExistsGenre + ": " + genreDto.name());
        }

        Genre genre = new Genre();
        genre.setName(genreDto.name().toLowerCase());

        genreDAO.save(genre);
    }

    @Override
    public void updateGenre(Long id, GenreDTO genreDto) {
        if (genreDAO.existsById(id)) {
            throw new EntityNotFoundException(StringConfig.errorAlreadyExistsGenre + ": " + id);
        }

        if (genreDAO.existsByName(genreDto.name().toLowerCase())) {
            throw new EntityNotFoundException(StringConfig.errorAlreadyExistsGenre + ": " + genreDto.name());
        }

        Genre genre = getGenreById(id);
        genre.setName(genreDto.name().toLowerCase());

        genreDAO.save(genre);
    }

    @Override
    public void deleteGenre(Long id) {
        if (!genreDAO.existsById(id)) {
            throw new EntityNotFoundException(StringConfig.errorNotFoundGenre + ": " + id);
        }

        genreDAO.deleteById(id);
    }

    /// INTERNAL METHODS
    public interface GenreIdsProvider {
        Set<Long> genreIds();
    }

    public <T extends GenreIdsProvider> Set<Genre> hashSetGenres(T t) {
        Set<Genre> genres = new HashSet<>();
        for (Long genreId : t.genreIds()) {
            Genre genre = getGenreById(genreId);
            genres.add(genre);
        }
        return genres;
    }
}
