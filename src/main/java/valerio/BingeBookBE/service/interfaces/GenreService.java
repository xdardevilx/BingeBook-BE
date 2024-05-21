package valerio.BingeBookBE.service.interfaces;

import java.util.List;

import valerio.BingeBookBE.dto.GenreDTO;
import valerio.BingeBookBE.entity.Genre;

public interface GenreService {
    Genre getGenreById(Long id);

    Genre getGenreByName(String name);

    List<Genre> getAllGenres();

    void createGenre(GenreDTO genreDto);

    void updateGenre(Long id, GenreDTO genreDto);
    
    void deleteGenre(Long id);
}
