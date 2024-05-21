package valerio.BingeBookBE.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import valerio.BingeBookBE.entity.Genre;

public interface GenreDAO extends JpaRepository<Genre, Long> {

    public @NonNull Optional<Genre> findById(@NonNull Long id);

    public Optional<Genre> findByName(String name);

    public boolean existsById(@NonNull Long id);

    public boolean existsByName(String name);
}
