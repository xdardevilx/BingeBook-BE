package valerio.BingeBookBE.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import valerio.BingeBookBE.dto.GenreDTO;
import valerio.BingeBookBE.dto.ResponseDTO;
import valerio.BingeBookBE.entity.Genre;
import valerio.BingeBookBE.entity.SerieTv;
import valerio.BingeBookBE.repositories.GenreDAO;
import valerio.BingeBookBE.repositories.SerieTvDAO;

import java.math.BigInteger;
import java.util.List;

@Service
public class GenreService {

    @Autowired
    private GenreDAO genreDAO;

    @Autowired
    private SerieTvDAO serieTvDAO;

    ///Create a new genre
    public ResponseDTO<Genre> save(GenreDTO genreDto) {

        Genre genre = new Genre();
        genre.setName(genreDto.name().toLowerCase());

        return new ResponseDTO<Genre>("success", genreDAO.save(genre));
    }

    public Genre findById(BigInteger id) {
        return genreDAO.findById(id).orElseThrow(() -> new IllegalArgumentException("genre non trovato"));
    }

    ///Get all genres
    public Page<Genre> getListGenres(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.genreDAO.findAll(pageable);
    }

    ///Assign list of genre ids to a serieTv
    public void assignGenreIdsToSerieTV(List<BigInteger> genreIds, BigInteger serieTvId) {
        for (BigInteger genreId : genreIds) {
            assignGenreIdToSerieTv(genreId, serieTvId);
        }
    }

    ///Assign genre id to a serieTv
    private void assignGenreIdToSerieTv(BigInteger genreId, BigInteger serieTvId) {
        SerieTv serieTv = serieTvDAO.findById(serieTvId).orElseThrow(() -> new IllegalArgumentException("serie tv non trovata"));
        Genre genre = genreDAO.findById(genreId).orElseThrow(() -> new IllegalArgumentException("genere non trovato"));
        serieTv.getGenres().add(genre);
        serieTvDAO.save(serieTv);
    }

//    public void associateGenreToSerieTv(BigInteger genreId, BigInteger serieTvId) {
//        SerieTv serieTv = serieTvDAO.findById(serieTvId).orElseThrow(() -> new IllegalArgumentException("serie tv non trovata"));
//        Genre genre = genreDAO.findById(genreId).orElseThrow(() -> new IllegalArgumentException("genere non trovato"));
//        serieTv.getGenres().add(genre);
//        serieTvDAO.save(serieTv);
//    }
}

