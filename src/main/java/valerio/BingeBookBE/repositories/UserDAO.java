package valerio.BingeBookBE.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import valerio.BingeBookBE.entity.User;

import java.math.BigInteger;
import java.util.Optional;

public interface UserDAO extends JpaRepository<User, BigInteger> {

    public Optional<User> findById(BigInteger l);

    public Optional<User> findByEmail(String email);

    public Optional<User> findByUsername(String username);
}
