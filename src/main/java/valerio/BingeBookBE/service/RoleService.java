package valerio.BingeBookBE.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import valerio.BingeBookBE.config.StringConfig;
import valerio.BingeBookBE.dto.RoleDTO;
import valerio.BingeBookBE.entity.Role;
import valerio.BingeBookBE.repositories.RoleDAO;

import java.math.BigInteger;

public class RoleService {
    final private RoleDAO roleDAO;

    @Autowired
    RoleService(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    ///CREATE
    public Role createRole(RoleDTO roleDTO) {
        Role role = new Role();
        role.setName(roleDTO.name());

        return roleDAO.save(role);
    }

    ///READ

    public Role getRoleById(BigInteger id) {
        return roleDAO.findById(id).orElse(null);
    }

    public Role getRoleByName(String name) {
        return roleDAO.findByName(name);
    }

    public Page<Role> getListRoles(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return roleDAO.findAll(pageable);
    }

    ///UPDATE
    public Role updateRole(BigInteger id, RoleDTO roleDTO) {
        Role role = roleDAO.findById(id).orElseThrow(() -> new IllegalArgumentException(StringConfig.errorNotFoundRole + ": " + id));
        role.setName(roleDTO.name());
        return roleDAO.save(role);
    }

    ///DELETE
    public void deleteRole(BigInteger id) {
        roleDAO.deleteById(id);
    }

}
