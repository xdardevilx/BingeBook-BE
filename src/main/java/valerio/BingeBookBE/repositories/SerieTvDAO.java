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
}
