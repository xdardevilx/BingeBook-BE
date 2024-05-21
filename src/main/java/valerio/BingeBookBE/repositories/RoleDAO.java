package valerio.BingeBookBE.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import valerio.BingeBookBE.entity.Role;

public interface RoleDAO extends JpaRepository<Role, Long> {

    public Optional<Role> findByName(String name);

    public boolean existsByName(String name);
}
