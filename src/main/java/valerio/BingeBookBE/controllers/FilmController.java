package valerio.BingeBookBE.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import valerio.BingeBookBE.dto.FilmDTO;
import valerio.BingeBookBE.entity.Film;
import valerio.BingeBookBE.entity.User;
import valerio.BingeBookBE.repositories.UserDAO;
import valerio.BingeBookBE.security.JWTTools;
import valerio.BingeBookBE.service.FilmService;

import org.springframework.data.domain.Page;

@RestController
@RequestMapping("/films")
public class FilmController {

    final private FilmService filmService;
    final private UserDAO userDAO;
    final private JWTTools jwtTools;

    @Autowired
    FilmController(FilmService filmService, UserDAO userDAO, JWTTools jwtTools) {
        this.filmService = filmService;
        this.userDAO = userDAO;
        this.jwtTools = jwtTools;
    }

    @PostMapping("/create")
    public ResponseEntity<Film> createFilm(@RequestHeader("Authorization") String authToken,
            @RequestBody @Validated FilmDTO filmDTO) throws IOException {
        System.out.println("createFilm");

        String token = null;
        if (authToken != null && authToken.startsWith("Bearer ")) {
            // Extract the token by removing the "Bearer " prefix
            token = authToken.substring(7); // 7 is the length of "Bearer "

            // Now you have the token without the "Bearer" prefix
            System.out.println("Token: " + token);
        } else {
            // Handle case where header doesn't start with "Bearer "
            // (e.g., invalid format)
            System.out.println("Invalid authorization header format");
        }

        String id = jwtTools.extractIdFromToken(token);

        User user = userDAO.findById(new BigInteger(id)).orElse(null);

        Film film = this.filmService.createFilm(filmDTO, user);

        return new ResponseEntity<Film>(film, HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public Page<Film> getListFilms(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {

        return this.filmService.getListFilms(page, size, sortBy);
    }

}
