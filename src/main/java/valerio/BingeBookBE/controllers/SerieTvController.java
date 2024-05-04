package valerio.BingeBookBE.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import valerio.BingeBookBE.dto.SerieTvDTO;
import valerio.BingeBookBE.entity.SerieTv;
import valerio.BingeBookBE.service.GenreService;
import valerio.BingeBookBE.service.SerieTvService;

@RestController
@RequestMapping("/series")
public class SerieTvController {

    private final SerieTvService serieTvService;
    private final GenreService genreService;

    @Autowired
    private SerieTvController(SerieTvService serieTvService, GenreService genreService) {
        this.serieTvService = serieTvService;
        this.genreService = genreService;
    }

    @PostMapping("/add")
    public ResponseEntity<SerieTv> createSerieTv(@RequestBody SerieTvDTO serieTvDTO) {

        //create serie
        SerieTv serieTv = serieTvService.save(serieTvDTO);

        //assign genre ids to serie
//        for (Genre e : serieTvDTO.genreIds()) {
//
//            Genre genre = genreService.findById(e.getId());
//
//            if (genre != null) {
//                List<Genre> genres = serieTv.getGenres();
//
//                if (genres == null) {
//                    genres = new java.util.ArrayList<Genre>();
//                }
//                genres.add(genre);
//
//                serieTv.setGenres(genres);
//            }
//
//        }

//        SerieTv a = serieTvService.save(SerieTvDTO.toDto(serieTv));

//        return ResponseEntity.ok(a);
        return new ResponseEntity<>(serieTv, HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public Page<SerieTv> getListGenres(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "10") int size,
                                       @RequestParam(defaultValue = "id") String sortBy) {
        return serieTvService.getListSerieTv(page, size, sortBy);
    }


//    @PutMapping("/edit/{serieTvId}")
//    public ResponseEntity<SerieTv> editSerieTv(@PathVariable BigInteger serieTvId, @RequestBody SerieTvDTO serieTvDTO) {
//
//        SerieTv serieTv = serieTvService.findById(serieTvId);
//
//        if (serieTv == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//        serieTv.getGenres().clear();
//
//        for (Genre e : serieTvDTO.genreIds()) {
//
//            Genre genre = genreService.findById(e.getId());
//
//            if (genre != null) {
//                List<Genre> genres = serieTv.getGenres();
//
//                if (genres == null) {
//                    genres = new java.util.ArrayList<Genre>();
//                }
//                genres.add(genre);
//
//                serieTv.setGenres(genres);
//            }
//
//        }
//
//        serieTvService.save(SerieTvDTO.toDto(serieTv));
//
//        return ResponseEntity.ok().build();
//    }
}
