package valerio.BingeBookBE.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import valerio.BingeBookBE.entity.Tag;

import java.math.BigInteger;

public interface TagDAO extends JpaRepository<Tag, BigInteger> {
}
