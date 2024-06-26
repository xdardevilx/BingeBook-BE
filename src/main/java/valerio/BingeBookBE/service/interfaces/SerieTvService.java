package valerio.BingeBookBE.service.interfaces;

import valerio.BingeBookBE.dto.SerieTvDTO;
import valerio.BingeBookBE.dto.response.PaginatedResponse;
import valerio.BingeBookBE.dto.search_criteria.SearchCriteriaSerieTvDTO;
import valerio.BingeBookBE.entity.SerieTv;

public interface SerieTvService {

    SerieTv getSerieTvById(Long idSerieTv);

    PaginatedResponse<SerieTv> getAllSeriesTvWithPagination(Long idUser, int page, int size, String sortBy);

    SerieTv createSerieTv(SerieTvDTO serieTvDTO, Long idUser);

    void updateSerieTv(Long idSerieTv, SerieTvDTO serieTvDTO);

    void deleteSerieTv(Long idSerieTv);

    PaginatedResponse<SerieTv> getSeriesTvByUserRefAndSearchCriteriaWithPagination(Long idUser,
            SearchCriteriaSerieTvDTO searchCriteria, int page, int size, String sortBy);
}
