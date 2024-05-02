package valerio.BingeBookBE.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import valerio.BingeBookBE.entity.Film;

import java.math.BigInteger;

public interface FilmDAO extends JpaRepository<Film, BigInteger> {
}
