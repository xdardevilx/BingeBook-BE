package valerio.BingeBookBE.service.interfaces;

import valerio.BingeBookBE.dto.FilmDTO;
import valerio.BingeBookBE.dto.response.PaginatedResponse;
import valerio.BingeBookBE.dto.search_criteria.SearchCreiteriaFilmDTO;
import valerio.BingeBookBE.entity.Film;

public interface FilmService {
    Film getFilmById(Long idFilm);

    PaginatedResponse<Film> getAllFilmsWithPagination(Long idUser, int page, int size, String sortBy);

    void createFilm(FilmDTO filmDTO, Long idUser);

    void updateFilm(Long idFilm, FilmDTO filmDTO);

    void deleteFilm(Long idFilm);

    PaginatedResponse<Film> getFilmsBySearchCriteriaWithPagination(Long idUser, SearchCreiteriaFilmDTO searchCriteria,
            int page, int size, String sortBy);
}
