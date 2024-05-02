package valerio.BingeBookBE.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import valerio.BingeBookBE.entity.Genre;

import java.math.BigInteger;

public interface GenreDAO extends JpaRepository<Genre, BigInteger> {
}
