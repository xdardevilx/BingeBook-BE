package valerio.BingeBookBE.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import valerio.BingeBookBE.entity.Tag;
import valerio.BingeBookBE.entity.User;

import java.util.List;
import java.util.Optional;

public interface TagDAO extends JpaRepository<Tag, Long> {

    public @NonNull Optional<Tag> findById(@NonNull Long id);

    @Query("SELECT t FROM Tag t WHERE t.userRef = :userRef")
    public List<Tag> findAllByUserRef(User userRef);

    @Query("SELECT t FROM Tag t WHERE t.name = :name AND t.userRef = :userRef")
    public Optional<Tag> findByNameByUserRef(String name, User userRef);

    public boolean existsById(@NonNull Long id);

    public boolean existsByName(String name);
}
