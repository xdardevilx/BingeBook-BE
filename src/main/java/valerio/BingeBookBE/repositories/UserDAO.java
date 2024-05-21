package valerio.BingeBookBE.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import valerio.BingeBookBE.entity.User;

import java.util.Optional;

public interface UserDAO extends JpaRepository<User, Long> {

    public @NonNull Optional<User> findById(@NonNull Long l);

    public Optional<User> findByEmail(@NonNull String email);

    public Optional<User> findByEmailOrUsername(String email, String username);

    public boolean existsById(@NonNull Long id);

    public boolean existsByEmail(@NonNull String email);

    public boolean existsByEmailOrUsername(String email, String username);


}
