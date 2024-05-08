package valerio.BingeBookBE.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;

import valerio.BingeBookBE.dto.FilmDTO;
import valerio.BingeBookBE.entity.Film;
import valerio.BingeBookBE.entity.User;
import valerio.BingeBookBE.repositories.UserDAO;
import valerio.BingeBookBE.service.FilmService;

@RestController
@RequestMapping("/films")
public class FilmController {

    final private FilmService filmService;
    final private UserDAO userDAO;

    FilmController(FilmService filmService, UserDAO userDAO) {
        this.filmService = filmService;
        this.userDAO = userDAO;
    }

    @PostMapping("/create")
    public ResponseEntity<Film> createFilm(@RequestBody @Validated FilmDTO filmDTO) {
        // Get authenticated user details
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        User user = userDAO.findByUsername(userDetails.getUsername()).orElse(null);

        Film film = this.filmService.createFilm(filmDTO, user);

        return new ResponseEntity<Film>(film, HttpStatus.CREATED);
    }

}
