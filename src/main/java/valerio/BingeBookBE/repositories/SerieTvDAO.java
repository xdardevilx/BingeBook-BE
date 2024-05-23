package valerio.BingeBookBE.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import valerio.BingeBookBE.entity.SerieTv;

public interface SerieTvDAO extends JpaRepository<SerieTv, Long> {

    @Query("SELECT f FROM SerieTv f WHERE f.userRef.id = :idUser")
    Page<SerieTv> findAllByUserRef(@Param("idUser") Long idUser, Pageable pageable);

    @Query("SELECT f FROM SerieTv f LEFT JOIN f.genres g LEFT JOIN f.tags t WHERE f.userRef.id = :idUser " +
           "AND (:title IS NULL OR f.title LIKE %:title%) " +
           "AND (:genreId IS NULL OR g.id = :genreId) " +
           "AND (:tagId IS NULL OR t.id = :tagId)")
    Page<SerieTv> findByUserRefAndSearchCriteria(
        @Param("idUser") Long idUser,
        @Param("title") String title,
        @Param("genreId") Long genreId,
        @Param("tagId") Long tagId,
        Pageable pageable);
}
