package valerio.BingeBookBE.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import valerio.BingeBookBE.entity.Film;

public interface FilmDAO extends JpaRepository<Film, Long> {

    @Query("SELECT f FROM Film f WHERE f.userRef.id = :idUser")
    Page<Film> findAllByUserRef(@Param("idUser") Long idUser, Pageable pageable);

    @Query("SELECT f FROM Film f LEFT JOIN f.genres g LEFT JOIN f.tags t WHERE f.userRef.id = :idUser " +
           "AND (:title IS NULL OR f.title LIKE %:title%) " +
           "AND (:genreId IS NULL OR g.id = :genreId) " +
           "AND (:tagId IS NULL OR t.id = :tagId)")
    Page<Film> findByUserRefAndSearchCriteria(
        @Param("idUser") Long idUser,
        @Param("title") String title,
        @Param("genreId") Long genreId,
        @Param("tagId") Long tagId,
        Pageable pageable);
}
