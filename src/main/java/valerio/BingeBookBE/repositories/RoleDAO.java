package valerio.BingeBookBE.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import valerio.BingeBookBE.entity.Role;

import java.math.BigInteger;

public interface RoleDAO extends JpaRepository<Role, BigInteger> {
}
