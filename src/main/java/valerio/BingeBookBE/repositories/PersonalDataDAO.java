package valerio.BingeBookBE.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import valerio.BingeBookBE.entity.PersonalData;

import java.util.Optional;

public interface PersonalDataDAO extends JpaRepository<PersonalData, Long> {

    public @NonNull Optional<PersonalData> findById(@NonNull Long id);
}
