package valerio.BingeBookBE.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import valerio.BingeBookBE.dto.SerieTvDTO;
import valerio.BingeBookBE.dto.response.PaginatedResponse;
import valerio.BingeBookBE.dto.search_criteria.SearchCreiteriaSerieTvDTO;
import valerio.BingeBookBE.entity.SerieTv;
import valerio.BingeBookBE.repositories.SerieTvDAO;
import valerio.BingeBookBE.service.interfaces.SerieTvService;

@Service
public class SerieTvServiceImpl implements SerieTvService{

    private final SerieTvDAO serieTvDAO;
    private final CloudinaryService cloudinaryService;
    private final GenreServiceImpl genreService;
    private final TagServiceImpl tagService;
    private final UserServiceImpl userService;

    @Autowired
    SerieTvServiceImpl(
            SerieTvDAO serieTvDAO,
            CloudinaryService cloudinaryService,
            GenreServiceImpl genreService,
            TagServiceImpl tagService,
            UserServiceImpl userService) {
        this.serieTvDAO = serieTvDAO;
        this.cloudinaryService = cloudinaryService;
        this.genreService = genreService;
        this.tagService = tagService;
        this.userService = userService;
    }

    @Override
    public SerieTv getSerieTvById(Long idSerieTv) {
        return serieTvDAO.findById(idSerieTv)
                .orElseThrow(() -> new IllegalArgumentException("SerieTv not found with ID: " + idSerieTv));
    }

    @Override
    public PaginatedResponse<SerieTv> getAllSeriesTvWithPagination(Long idUser, int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<SerieTv> serieTvPage = this.serieTvDAO.findAllByUserRef(idUser, pageable);
        return new PaginatedResponse<>(serieTvPage.getContent(), serieTvPage.getNumber(), serieTvPage.getSize(),
                serieTvPage.getTotalPages(), serieTvPage.getTotalElements(), serieTvPage.getSort().toString());
    }

    @Override
    public SerieTv createSerieTv(SerieTvDTO serieTvDTO, Long idUser) {
        SerieTv serieTv = new SerieTv();

        serieTv.setTitle(serieTvDTO.title());
        serieTv.setLastEpisodeViewed(serieTvDTO.lastEpisodeViewed());
        serieTv.setLastEpisodeViewedSeason(serieTvDTO.lastEpisodeViewedSeason());

        if (serieTvDTO.posterUrl() != null) {
            serieTv.setPosterUrl(cloudinaryService.uploadImageFromMultipartFile(serieTvDTO.posterUrl()));
        }

        if (serieTvDTO.genreIds() != null) {
            serieTv.setGenres(genreService.hashSetGenres(serieTvDTO));
        }

        if (serieTvDTO.tagIds() != null) {
            serieTv.setTags(tagService.hashSetTags(serieTvDTO));
        }

        serieTv.setUserRef(userService.getUserById(idUser));

        return serieTvDAO.save(serieTv);
    }

    @Override
    public void updateSerieTv(Long idSerieTv, SerieTvDTO serieTvDTO) {
        SerieTv serieTv = getSerieTvById(idSerieTv);

        serieTv.setTitle(serieTvDTO.title());
        serieTv.setLastEpisodeViewed(serieTvDTO.lastEpisodeViewed());
        serieTv.setLastEpisodeViewedSeason(serieTvDTO.lastEpisodeViewedSeason());

        if (serieTvDTO.posterUrl() != null) {
            serieTv.setPosterUrl(cloudinaryService.uploadImageFromMultipartFile(serieTvDTO.posterUrl()));
        }

        if (serieTvDTO.genreIds() != null) {
            serieTv.setGenres(genreService.hashSetGenres(serieTvDTO));
        }

        if (serieTvDTO.tagIds() != null) {
            serieTv.setTags(tagService.hashSetTags(serieTvDTO));
        }

        serieTvDAO.save(serieTv);
    }

    @Override
    public void deleteSerieTv(Long idSerieTv) {
        try {
            serieTvDAO.deleteById(idSerieTv);
        } catch (Exception e) {
            throw new IllegalArgumentException("SerieTv not found with ID: " + idSerieTv);
        }
    }

    @Override
    public PaginatedResponse<SerieTv> getSeriesTvBySearchCriteriaWithPagination(Long idUser,
            SearchCreiteriaSerieTvDTO searchCriteria, int page, int size, String sortBy) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSeriesTvBySearchCriteriaWithPagination'");
    }

    // /// CREATE
    // public SerieTv createSerieTv(SerieTvDTO serieTvDto, User user) throws IOException {

    //     SerieTv serieTv = new SerieTv();
    //     serieTv.setTitle(serieTvDto.title().toLowerCase());
    //     serieTv.setLastEpisodeViewed(serieTvDto.lastEpisodeViewed());
    //     serieTv.setLastEpisodeViewedSeason(serieTvDto.lastEpisodeViewedSeason());
    //     // serieTv.setPosterUrl(cloudinary.url().generate(serieTvDto.posterUrl()));

    //     String url = (String) cloudinary.uploader().upload(serieTvDto.posterUrl().getBytes(), ObjectUtils.emptyMap())
    //             .get("url");

    //     serieTv.setPosterUrl(url);

    //     Set<Genre> genres = new HashSet<>();
    //     for (Long genreId : serieTvDto.genreIds()) {
    //         Genre genre = genreDAO.findById(genreId)
    //                 .orElseThrow(() -> new IllegalArgumentException("Genre not found with ID: " + genreId));
    //         genres.add(genre);
    //     }
    //     serieTv.setGenres(genres);

    //     Set<Tag> tags = new HashSet<>();
    //     for (Long tagId : serieTvDto.tagIds()) {
    //         Tag tag = tagDAO.findById(tagId)
    //                 .orElseThrow(() -> new IllegalArgumentException("Tag not found with ID: " + tagId));
    //         tags.add(tag);
    //     }
    //     serieTv.setTags(tags);

    //     serieTv.setUserRef(user);

    //     return serieTvDAO.save(serieTv);
    // }

    // /// READ
    // public SerieTv getSerieTvById(Long id) {
    //     return serieTvDAO.findById(id).orElse(null);
    // }

    // public Page<SerieTv> getListSerieTv(int page, int size, String sortBy) {
    //     Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
    //     return this.serieTvDAO.findAll(pageable);
    // }

    // /// UPDATE
    // public SerieTv updateSerieTv(Long idSerieTv, SerieTvDTO serieTvDto, User user) throws IOException {
    //     SerieTv serieTv = serieTvDAO.findById(idSerieTv).orElse(null);
    //     if (serieTv == null) {
    //         return null;
    //     }

    //     serieTv.setTitle(serieTvDto.title().toLowerCase());
    //     serieTv.setLastEpisodeViewed(serieTvDto.lastEpisodeViewed());
    //     serieTv.setLastEpisodeViewedSeason(serieTvDto.lastEpisodeViewedSeason());
    //     String url = (String) cloudinary.uploader().upload(serieTvDto.posterUrl().getBytes(), ObjectUtils.emptyMap())
    //             .get("url");

    //     serieTv.setPosterUrl(url);

    //     Set<Genre> genres = new HashSet<>();
    //     for (Long genreId : serieTvDto.genreIds()) {
    //         Genre genre = genreDAO.findById(genreId)
    //                 .orElseThrow(() -> new IllegalArgumentException("Genre not found with ID: " + genreId));
    //         genres.add(genre);
    //     }
    //     serieTv.setGenres(genres);

    //     Set<Tag> tags = new HashSet<>();
    //     for (Long tagId : serieTvDto.tagIds()) {
    //         Tag tag = tagDAO.findById(tagId)
    //                 .orElseThrow(() -> new IllegalArgumentException("Tag not found with ID: " + tagId));
    //         tags.add(tag);
    //     }
    //     serieTv.setTags(tags);

    //     serieTv.setUserRef(user);

    //     return serieTvDAO.save(serieTv);
    // }

    // /// DELETE
    // public void deleteSerieTv(Long id) {
    //     serieTvDAO.deleteById(id);
    // }
}
