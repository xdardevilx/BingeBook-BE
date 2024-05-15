package valerio.BingeBookBE.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import valerio.BingeBookBE.config.StringConfig;
import valerio.BingeBookBE.dto.FilmDTO;
import valerio.BingeBookBE.dto.PageableDTO;
import valerio.BingeBookBE.entity.Film;
import valerio.BingeBookBE.entity.Genre;
import valerio.BingeBookBE.entity.Tag;
import valerio.BingeBookBE.entity.User;
import valerio.BingeBookBE.repositories.FilmDAO;

import java.io.IOException;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

@Service
public class FilmService {

    private final FilmDAO filmDAO;
    private final Cloudinary cloudinary;
    private final GenreService genreService;
    private final TagService tagService;

    @Autowired
    FilmService(FilmDAO filmDAO, Cloudinary cloudinary, GenreService genreService, TagService tagService) {
        this.filmDAO = filmDAO;
        this.cloudinary = cloudinary;
        this.genreService = genreService;
        this.tagService = tagService;
    }

    public Film getFilmById(BigInteger idFilm) {
        return filmDAO.findById(idFilm)
                .orElseThrow(() -> new IllegalArgumentException(StringConfig.errorNotFoundFilm + ": " + idFilm));
    }

    private Film saveFilm(Film film, FilmDTO filmDTO, User user) {

        film.setTitle(filmDTO.title());

        if (filmDTO.genreIds() != null) {
            film.setGenres(genreService.hashSetGenres(filmDTO));
        }

        if (filmDTO.tagIds() != null) {
            Set<Tag> tags = new HashSet<>();
            for (BigInteger tagId : filmDTO.tagIds()) {
                Tag tag = tagService.getTagById(tagId);
                tags.add(tag);
            }
            film.setTags(tags);
        }

        if (filmDTO.posterUrl() != null) {
            try {
                String url = (String) cloudinary.uploader()
                        .upload(filmDTO.posterUrl().getBytes(), ObjectUtils.emptyMap())
                        .get("url");
                film.setPosterUrl(url);
            } catch (IOException e) {
                throw new IllegalArgumentException(StringConfig.errorUploadImage);
            }
        }

        film.setUserRef(user);

        return filmDAO.save(film);
    }

    public Film createFilm(FilmDTO filmDTO, User user) {
        Film film = new Film();

        return saveFilm(film, filmDTO, user);
    }

    public PageableDTO<Film> getListFilms(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Film> filmPage = this.filmDAO.findAll(pageable);
        return new PageableDTO<>(
                filmPage.getNumber(),
                filmPage.getSize(),
                filmPage.getTotalPages(),
                filmPage.getTotalElements(),
                sortBy,
                filmPage.getContent());
    }

    public Film updateFilm(BigInteger idFilm, FilmDTO filmDTO, User user) {
        Film film = getFilmById(idFilm);

        return saveFilm(film, filmDTO, user);
    }

    public void deleteFilm(BigInteger idFilm) {
        if (getFilmById(idFilm) != null) {   
            filmDAO.deleteById(idFilm);
        }else{
            throw new IllegalArgumentException(StringConfig.errorNotFoundFilm + ": " + idFilm);
        }
    }
}
