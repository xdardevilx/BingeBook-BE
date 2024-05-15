package valerio.BingeBookBE.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import valerio.BingeBookBE.dto.GenreDTO;
import valerio.BingeBookBE.entity.Genre;
import valerio.BingeBookBE.service.GenreService;
import valerio.BingeBookBE.utils.ResponseEntityCustom;

@RestController
@RequestMapping("/genres")
public class GenreController {
    private final GenreService genreService;

    @Autowired
    GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/list")
    public ResponseEntity<?> getListGenres(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size,
                                     @RequestParam(defaultValue = "id") String sortBy) {
        return ResponseEntityCustom.responseSuccess(this.genreService.getListGenres(page, size, sortBy), HttpStatus.OK);
    }

    @PostMapping("/add")
    public Genre updateProfile(@RequestBody @Validated GenreDTO genre) {

        return this.genreService.saveGenre(genre);
    }

}
