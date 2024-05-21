package valerio.BingeBookBE.service.interfaces;

import java.util.List;

import valerio.BingeBookBE.dto.RoleDTO;
import valerio.BingeBookBE.entity.Role;

public interface RoleService {
    Role getRoleById(Long id);

    Role getRoleByName(String name);

    List<Role> getAllRoles();

    void createRole(RoleDTO roleDTO);

    void updateRole(Long id, RoleDTO roleDTO);

    void deleteRole(Long id);

}
