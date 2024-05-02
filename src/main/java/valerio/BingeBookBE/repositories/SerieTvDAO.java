package valerio.BingeBookBE.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import valerio.BingeBookBE.entity.SerieTv;

import java.math.BigInteger;

public interface SerieTvDAO extends JpaRepository<SerieTv, BigInteger> {
}
