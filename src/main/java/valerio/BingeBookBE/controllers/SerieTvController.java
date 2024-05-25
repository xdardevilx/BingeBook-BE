package valerio.BingeBookBE.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import valerio.BingeBookBE.dto.SerieTvDTO;
import valerio.BingeBookBE.dto.search_criteria.SearchCriteriaSerieTvDTO;
import valerio.BingeBookBE.service.SerieTvServiceImpl;
import valerio.BingeBookBE.utils.ResponseEntityCustom;

@RestController
@RequestMapping("/series")
public class SerieTvController {

    private final SerieTvServiceImpl serieTvService;

    @Autowired
    private SerieTvController(SerieTvServiceImpl serieTvService) {
        this.serieTvService = serieTvService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createSerieTv(
            @RequestBody @Validated SerieTvDTO serieTvDto, HttpServletRequest request) {

        Long userId = (Long) request.getAttribute("userId");

        this.serieTvService.createSerieTv(serieTvDto, userId);

        return ResponseEntityCustom.responseSuccess("SerieTv create succesfully", HttpStatus.CREATED);
    }

    @GetMapping("/detail/{idSerieTv}")
    public ResponseEntity<?> getSerieTv(@PathVariable("idSerieTv") Long idSerieTv) {
        return ResponseEntityCustom.responseSuccess(this.serieTvService.getSerieTvById(idSerieTv), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getListSerieTvs(@RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "10") int size,
                                             @RequestParam(defaultValue = "id") String sortBy, HttpServletRequest request) {

        Long userId = (Long) request.getAttribute("userId");

        return ResponseEntityCustom.responseSuccess(
                this.serieTvService.getAllSeriesTvWithPagination(userId, page, size, sortBy), HttpStatus.OK);
    }

    @GetMapping("/pagination")
    public ResponseEntity<?> getListSerieTv(@RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size,
                                            @RequestParam(defaultValue = "id") String sortBy, HttpServletRequest request) {

        Long userId = (Long) request.getAttribute("userId");

        return ResponseEntityCustom
                .responseSuccess(this.serieTvService.getAllSeriesTvWithPagination(userId, page, size, sortBy), HttpStatus.OK);
    }

    @PutMapping("/update/{idSerieTv}")
    public ResponseEntity<?> updateSerieTv(
            @RequestBody @Validated SerieTvDTO serieTvDto,
            @PathVariable("idSerieTv") Long idSerieTv) {

        this.serieTvService.updateSerieTv(idSerieTv, serieTvDto);

        return ResponseEntityCustom.responseSuccess("SerieTv update succesfuly", HttpStatus.OK);
    }

    @PutMapping("/delete/{idSerieTv}")
    public ResponseEntity<?> deleteSerieTv(@PathVariable("idSerieTv") Long idSerieTv) {

        this.serieTvService.deleteSerieTv(idSerieTv);

        return ResponseEntityCustom.responseSuccess("SerieTv deleted", HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchFilms(
            @RequestBody @Validated SearchCriteriaSerieTvDTO searchCriteria,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy, HttpServletRequest request) {

        Long userId = (Long) request.getAttribute("userId");

        return ResponseEntityCustom.responseSuccess(
                serieTvService.getSeriesTvByUserRefAndSearchCriteriaWithPagination(userId, searchCriteria, page, size,
                        sortBy),
                HttpStatus.OK);
    }

}
