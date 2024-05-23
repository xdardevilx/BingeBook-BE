package valerio.BingeBookBE.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import valerio.BingeBookBE.dto.FilmDTO;
import valerio.BingeBookBE.dto.search_criteria.SearchCriteriaFilmDTO;
import valerio.BingeBookBE.service.FilmServiceImpl;
import valerio.BingeBookBE.utils.ResponseEntityCustom;

@RestController
@RequestMapping("/films")
public class FilmController {

    final private FilmServiceImpl filmService;

    @Autowired
    FilmController(FilmServiceImpl filmService) {
        this.filmService = filmService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createFilm(
            @RequestBody @Validated FilmDTO filmDTO, HttpServletRequest request) {

        Long userId = (Long) request.getAttribute("userId");

        this.filmService.createFilm(filmDTO, userId);

        return ResponseEntityCustom.responseSuccess("Film create succesfully", HttpStatus.CREATED);
    }

    @GetMapping("/detail/{idFilm}")
    public ResponseEntity<?> getFilm(@PathVariable("idFilm") Long idFilm) {
        return ResponseEntityCustom.responseSuccess(this.filmService.getFilmById(idFilm), HttpStatus.OK);
    }

    @GetMapping("/pagination")
    public ResponseEntity<?> getListFilms(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy, HttpServletRequest request) {

        Long userId = (Long) request.getAttribute("userId");

        return ResponseEntityCustom
                .responseSuccess(this.filmService.getAllFilmsWithPagination(userId, page, size, sortBy), HttpStatus.OK);
    }

    @PutMapping("/update/{idFilm}")
    public ResponseEntity<?> updateFilm(
            @RequestBody @Validated FilmDTO filmDTO,
            @PathVariable("idFilm") Long idFilm) {

        this.filmService.updateFilm(idFilm, filmDTO);

        return ResponseEntityCustom.responseSuccess("Film update succesfuly", HttpStatus.OK);
    }

    @PutMapping("/delete/{idFilm}")
    public ResponseEntity<?> deleteFilm(@PathVariable("idFilm") Long idFilm) {

        this.filmService.deleteFilm(idFilm);

        return ResponseEntityCustom.responseSuccess("Film deleted", HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchFilms(
        @RequestBody @Validated SearchCriteriaFilmDTO searchCriteria,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy, HttpServletRequest request) {

        Long userId = (Long) request.getAttribute("userId");

        return ResponseEntityCustom.responseSuccess(
                filmService.getFilmsByUserRefAndSearchCriteriaWithPagination(userId, searchCriteria, page, size, sortBy),
                HttpStatus.OK);
    }

}
