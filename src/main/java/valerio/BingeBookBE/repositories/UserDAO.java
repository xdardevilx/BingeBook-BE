package valerio.BingeBookBE.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import valerio.BingeBookBE.entity.User;

import java.math.BigInteger;

public interface UserDAO extends JpaRepository<User, BigInteger> {
}
