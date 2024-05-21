package valerio.BingeBookBE.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import valerio.BingeBookBE.dto.GenreDTO;
import valerio.BingeBookBE.service.GenreServiceImpl;
import valerio.BingeBookBE.utils.ResponseEntityCustom;

@RestController
@RequestMapping("/genres")
public class GenreController {
    private final GenreServiceImpl genreService;

    @Autowired
    GenreController(GenreServiceImpl genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getGenreById(@PathVariable Long id) {
        return ResponseEntityCustom.responseSuccess(this.genreService.getGenreById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getListGenres() {
        return ResponseEntityCustom.responseSuccess(this.genreService.getAllGenres(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createGenre(@Validated @RequestBody GenreDTO genreDTO) {
        this.genreService.createGenre(genreDTO);
        return ResponseEntityCustom.responseSuccess("Genre created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateGenre(@PathVariable Long id, @Validated @RequestBody GenreDTO genreDTO) {
        this.genreService.updateGenre(id, genreDTO);
        return ResponseEntityCustom.responseSuccess("Genre updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteGenre(@PathVariable Long id) {
        this.genreService.deleteGenre(id);
        return ResponseEntityCustom.responseSuccess("Genre deleted successfully", HttpStatus.OK);
    }

}
