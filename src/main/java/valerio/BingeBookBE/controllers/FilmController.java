package valerio.BingeBookBE.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import valerio.BingeBookBE.dto.FilmDTO;
import valerio.BingeBookBE.entity.Film;
import valerio.BingeBookBE.entity.User;
import valerio.BingeBookBE.security.JWTTools;
import valerio.BingeBookBE.service.FilmService;
import valerio.BingeBookBE.service.UserService;
import valerio.BingeBookBE.utils.ResponseEntityCustom;

@RestController
@RequestMapping("/films")
public class FilmController {

    final private FilmService filmService;
    final private UserService userService;
    final private JWTTools jwtTools;

    @Autowired
    FilmController(FilmService filmService, UserService userService, JWTTools jwtTools) {
        this.filmService = filmService;
        this.userService = userService;
        this.jwtTools = jwtTools;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createFilm(
            @RequestBody @Validated FilmDTO filmDTO, HttpServletRequest request){

        BigInteger userId = (BigInteger) request.getAttribute("userId");
        User user = userService.getUserById(userId);

        Film film = this.filmService.createFilm(filmDTO, user);

        return ResponseEntityCustom.responseSuccess(film, HttpStatus.CREATED);
    }

    @GetMapping("/detail/{idFilm}")
    public ResponseEntity<?> getFilm(@PathVariable("idFilm") BigInteger idFilm) {
        return ResponseEntityCustom.responseSuccess(this.filmService.getFilmById(idFilm), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getListFilms(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {

        return ResponseEntityCustom.responseSuccess(this.filmService.getListFilms(page, size, sortBy), HttpStatus.OK);
    }

    @PutMapping("/update/{idFilm}")
    public ResponseEntity<?> updateFilm(@RequestHeader("Authorization") String authToken,
            @RequestBody @Validated FilmDTO filmDTO,
            @PathVariable("idFilm") BigInteger idFilm) throws IOException {
        System.out.println("updateFilm");

        String token = null;
        if (authToken != null && authToken.startsWith("Bearer ")) {
            // Extract the token by removing the "Bearer " prefix
            token = authToken.substring(7); // 7 is the length of "Bearer "

        } else {
            // Handle case where header doesn't start with "Bearer "
            // (e.g., invalid format)
            throw new RuntimeException("Invalid token format");
        }

        BigInteger id = jwtTools.extractIdFromToken(token);

        User user = userService.getUserById(id);

        Film film = this.filmService.updateFilm(idFilm, filmDTO, user);

        return ResponseEntityCustom.responseSuccess(film, HttpStatus.OK);
    }

    @PutMapping("/delete/{idFilm}")
    public ResponseEntity<?> deleteFilm(@RequestHeader("Authorization") String authToken,
            @PathVariable("idFilm") BigInteger idFilm) {
        System.out.println("deleteFilm");

        String token = null;
        if (authToken != null && authToken.startsWith("Bearer ")) {
            // Extract the token by removing the "Bearer " prefix
            token = authToken.substring(7); // 7 is the length of "Bearer "

        } else {
            // Handle case where header doesn't start with "Bearer "
            // (e.g., invalid format)
            throw new RuntimeException("Invalid token format");
        }

        BigInteger id = jwtTools.extractIdFromToken(token);

        User user = userService.getUserById(id);

        if (user == null)
            return ResponseEntityCustom.responseError("User not found", HttpStatus.NOT_FOUND);

        this.filmService.deleteFilm(idFilm);

        return ResponseEntityCustom.responseSuccess("Film deleted", HttpStatus.OK);
    }

}
