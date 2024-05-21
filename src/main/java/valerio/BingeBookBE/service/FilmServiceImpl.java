package valerio.BingeBookBE.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import valerio.BingeBookBE.config.StringConfig;
import valerio.BingeBookBE.dto.FilmDTO;
import valerio.BingeBookBE.dto.response.PaginatedResponse;
import valerio.BingeBookBE.dto.search_criteria.SearchCreiteriaFilmDTO;
import valerio.BingeBookBE.entity.Film;
import valerio.BingeBookBE.repositories.FilmDAO;
import valerio.BingeBookBE.service.interfaces.FilmService;

@Service
public class FilmServiceImpl implements FilmService {

    private final FilmDAO filmDAO;
    private final CloudinaryService cloudinaryService;
    private final GenreServiceImpl genreService;
    private final TagServiceImpl tagService;
    private final UserServiceImpl userService;

    @Autowired
    FilmServiceImpl(
            FilmDAO filmDAO,
            CloudinaryService cloudinaryService,
            GenreServiceImpl genreService,
            TagServiceImpl tagService,
            UserServiceImpl userService) {
        this.filmDAO = filmDAO;
        this.cloudinaryService = cloudinaryService;
        this.genreService = genreService;
        this.tagService = tagService;
        this.userService = userService;
    }

    @Override
    public Film getFilmById(Long idFilm) {
        return filmDAO.findById(idFilm)
                .orElseThrow(() -> new IllegalArgumentException(StringConfig.errorNotFoundFilm + ": " + idFilm));
    }

    @Override
    public PaginatedResponse<Film> getAllFilmsWithPagination(Long idUser, int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Film> filmPage = this.filmDAO.findAllByUserRef(idUser, pageable);
        return new PaginatedResponse<>(filmPage.getContent(), filmPage.getNumber(), filmPage.getSize(),
                filmPage.getTotalPages(), filmPage.getTotalElements(), filmPage.getSort().toString());
    }

    @Override
    public void createFilm(FilmDTO filmDTO, Long idUser) {
        Film film = new Film();

        film.setTitle(filmDTO.title());

        if (filmDTO.genreIds() != null) {
            film.setGenres(genreService.hashSetGenres(filmDTO));
        }

        if (filmDTO.tagIds() != null) {
            film.setTags(tagService.hashSetTags(filmDTO));
        }

        if (filmDTO.posterUrl() != null) {
            cloudinaryService.uploadImageFromMultipartFile(filmDTO.posterUrl());
        }

        film.setUserRef(userService.getUserById(idUser));

        filmDAO.save(film);
    }

    @Override
    public void updateFilm(Long idFilm, FilmDTO filmDTO) {
        Film film = getFilmById(idFilm);

        film.setTitle(filmDTO.title());

        if (filmDTO.genreIds() != null) {
            film.setGenres(genreService.hashSetGenres(filmDTO));
        }

        if (filmDTO.tagIds() != null) {
            film.setTags(tagService.hashSetTags(filmDTO));
        }

        if (filmDTO.posterUrl() != null) {
            cloudinaryService.uploadImageFromMultipartFile(filmDTO.posterUrl());
        }

        filmDAO.save(film);
    }

    @Override
    public void deleteFilm(Long idFilm) {
        if (getFilmById(idFilm) != null) {
            filmDAO.deleteById(idFilm);
        } else {
            throw new IllegalArgumentException(StringConfig.errorNotFoundFilm + ": " + idFilm);
        }
    }

    @Override
    public PaginatedResponse<Film> getFilmsBySearchCriteriaWithPagination(Long idUser,
            SearchCreiteriaFilmDTO searchCriteria, int page, int size, String sortBy) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFilmsBySearchCriteriaWithPagination'");
    }

    // public Film getFilmById(Long idFilm) {
    // return filmDAO.findById(idFilm)
    // .orElseThrow(() -> new
    // IllegalArgumentException(StringConfig.errorNotFoundFilm + ": " + idFilm));
    // }

    // private Film saveFilm(Film film, FilmDTO filmDTO, User user) {

    // film.setTitle(filmDTO.title());

    // if (filmDTO.genreIds() != null) {
    // film.setGenres(genreService.hashSetGenres(filmDTO));
    // }

    // if (filmDTO.tagIds() != null) {
    // Set<Tag> tags = new HashSet<>();
    // for (Long tagId : filmDTO.tagIds()) {
    // Tag tag = tagService.getTagById(tagId);
    // tags.add(tag);
    // }
    // film.setTags(tags);
    // }

    // if (filmDTO.posterUrl() != null) {
    // cloudinaryService.uploadImageFromMultipartFile(filmDTO.posterUrl());
    // }

    // film.setUserRef(user);

    // return filmDAO.save(film);
    // }

    // public Film createFilm(FilmDTO filmDTO, User user) {
    // Film film = new Film();

    // return saveFilm(film, filmDTO, user);
    // }

    // public PageableDTO<Film> getListFilms(int page, int size, String sortBy) {
    // Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
    // Page<Film> filmPage = this.filmDAO.findAll(pageable);
    // return new PageableDTO<>(
    // filmPage.getNumber(),
    // filmPage.getSize(),
    // filmPage.getTotalPages(),
    // filmPage.getTotalElements(),
    // sortBy,
    // filmPage.getContent());
    // }

    // public Film updateFilm(Long idFilm, FilmDTO filmDTO, User user) {
    // Film film = getFilmById(idFilm);

    // return saveFilm(film, filmDTO, user);
    // }

    // public void deleteFilm(Long idFilm) {
    // if (getFilmById(idFilm) != null) {
    // filmDAO.deleteById(idFilm);
    // } else {
    // throw new IllegalArgumentException(StringConfig.errorNotFoundFilm + ": " +
    // idFilm);
    // }
    // }
}
