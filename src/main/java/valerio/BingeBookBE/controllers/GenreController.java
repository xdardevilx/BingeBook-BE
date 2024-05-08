package valerio.BingeBookBE.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import valerio.BingeBookBE.dto.GenreDTO;
import valerio.BingeBookBE.entity.Genre;
import valerio.BingeBookBE.service.GenreService;

@RestController
@RequestMapping("/genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping("/list")
    public Page<Genre> getListGenres(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size,
                                     @RequestParam(defaultValue = "id") String sortBy) {
        return genreService.getListGenres(page, size, sortBy);
    }

    @PostMapping("/add")
    public Genre updateProfile(@RequestBody @Validated GenreDTO genre) {

        return this.genreService.save(genre);
    }

}
