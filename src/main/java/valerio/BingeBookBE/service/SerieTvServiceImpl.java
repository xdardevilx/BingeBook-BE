package valerio.BingeBookBE.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import valerio.BingeBookBE.dto.SerieTvDTO;
import valerio.BingeBookBE.dto.response.PaginatedResponse;
import valerio.BingeBookBE.dto.search_criteria.SearchCriteriaSerieTvDTO;
import valerio.BingeBookBE.entity.SerieTv;
import valerio.BingeBookBE.repositories.SerieTvDAO;
import valerio.BingeBookBE.service.interfaces.SerieTvService;

@Service
public class SerieTvServiceImpl implements SerieTvService {

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
    public PaginatedResponse<SerieTv> getSeriesTvByUserRefAndSearchCriteriaWithPagination(Long idUser,
            SearchCriteriaSerieTvDTO searchCriteria, int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size);
        Page<SerieTv> serieTvPage = serieTvDAO.findByUserRefAndSearchCriteria(
                idUser,
                searchCriteria.title(),
                searchCriteria.genreId(),
                searchCriteria.tagId(),
                pageable);
        return new PaginatedResponse<>(serieTvPage.getContent(), serieTvPage.getNumber(), serieTvPage.getSize(),
                serieTvPage.getTotalPages(), serieTvPage.getTotalElements(), serieTvPage.getSort().toString());
    }
}
