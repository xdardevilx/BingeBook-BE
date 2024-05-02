package valerio.BingeBookBE.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import valerio.BingeBookBE.entity.PersonalData;

import java.math.BigInteger;

public interface PersonalDataDAO extends JpaRepository<PersonalData, BigInteger> {
}
