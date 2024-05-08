package valerio.BingeBookBE.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import valerio.BingeBookBE.dto.SerieTvDTO;
import valerio.BingeBookBE.entity.Genre;
import valerio.BingeBookBE.entity.SerieTv;
import valerio.BingeBookBE.entity.Tag;
import valerio.BingeBookBE.entity.User;
import valerio.BingeBookBE.repositories.GenreDAO;
import valerio.BingeBookBE.repositories.SerieTvDAO;
import valerio.BingeBookBE.repositories.TagDAO;

import java.io.IOException;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

@Service
public class SerieTvService {

    private final SerieTvDAO serieTvDAO;
    private final GenreDAO genreDAO;
    private final TagDAO tagDAO;
    private final Cloudinary cloudinary;

    @Autowired
    SerieTvService(SerieTvDAO serieTvDAO, GenreDAO genreDAO, TagDAO tagDAO, Cloudinary cloudinary) {
        this.serieTvDAO = serieTvDAO;
        this.genreDAO = genreDAO;
        this.tagDAO = tagDAO;
        this.cloudinary = cloudinary;
    }

    /// CREATE
    public SerieTv createSerieTv(SerieTvDTO serieTvDto, User user) throws IOException {

        SerieTv serieTv = new SerieTv();
        serieTv.setTitle(serieTvDto.title().toLowerCase());
        serieTv.setLastEpisodeViewed(serieTvDto.lastEpisodeViewed());
        serieTv.setLastEpisodeViewedSeason(serieTvDto.lastEpisodeViewedSeason());
        // serieTv.setPosterUrl(cloudinary.url().generate(serieTvDto.posterUrl()));

        String url = (String) cloudinary.uploader().upload(serieTvDto.posterUrl().getBytes(), ObjectUtils.emptyMap())
                .get("url");

        serieTv.setPosterUrl(url);

        Set<Genre> genres = new HashSet<>();
        for (BigInteger genreId : serieTvDto.genreIds()) {
            Genre genre = genreDAO.findById(genreId)
                    .orElseThrow(() -> new IllegalArgumentException("Genre not found with ID: " + genreId));
            genres.add(genre);
        }
        serieTv.setGenres(genres);

        Set<Tag> tags = new HashSet<>();
        for (BigInteger tagId : serieTvDto.tagIds()) {
            Tag tag = tagDAO.findById(tagId)
                    .orElseThrow(() -> new IllegalArgumentException("Tag not found with ID: " + tagId));
            tags.add(tag);
        }
        serieTv.setTags(tags);

        serieTv.setUserRef(user);

        return serieTvDAO.save(serieTv);
    }

    /// READ
    public SerieTv getSerieTvById(BigInteger id) {
        return serieTvDAO.findById(id).orElse(null);
    }

    public Page<SerieTv> getListSerieTv(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.serieTvDAO.findAll(pageable);
    }

    /// UPDATE
    public SerieTv updateSerieTv(BigInteger idSerieTv, SerieTvDTO serieTvDto, User user) throws IOException {
        SerieTv serieTv = serieTvDAO.findById(idSerieTv).orElse(null);
        if (serieTv == null) {
            return null;
        }

        serieTv.setTitle(serieTvDto.title().toLowerCase());
        serieTv.setLastEpisodeViewed(serieTvDto.lastEpisodeViewed());
        serieTv.setLastEpisodeViewedSeason(serieTvDto.lastEpisodeViewedSeason());
        String url = (String) cloudinary.uploader().upload(serieTvDto.posterUrl().getBytes(), ObjectUtils.emptyMap())
                .get("url");

        serieTv.setPosterUrl(url);

        Set<Genre> genres = new HashSet<>();
        for (BigInteger genreId : serieTvDto.genreIds()) {
            Genre genre = genreDAO.findById(genreId)
                    .orElseThrow(() -> new IllegalArgumentException("Genre not found with ID: " + genreId));
            genres.add(genre);
        }
        serieTv.setGenres(genres);

        Set<Tag> tags = new HashSet<>();
        for (BigInteger tagId : serieTvDto.tagIds()) {
            Tag tag = tagDAO.findById(tagId)
                    .orElseThrow(() -> new IllegalArgumentException("Tag not found with ID: " + tagId));
            tags.add(tag);
        }
        serieTv.setTags(tags);

        serieTv.setUserRef(user);

        return serieTvDAO.save(serieTv);
    }

    /// DELETE
    public void deleteSerieTv(BigInteger id) {
        serieTvDAO.deleteById(id);
    }
}
