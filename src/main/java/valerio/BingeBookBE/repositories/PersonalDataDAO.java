package valerio.BingeBookBE.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import valerio.BingeBookBE.entity.PersonalData;

import java.math.BigInteger;
import java.util.Optional;

public interface PersonalDataDAO extends JpaRepository<PersonalData, BigInteger> {

    public Optional<PersonalData> findById(BigInteger id);
}
