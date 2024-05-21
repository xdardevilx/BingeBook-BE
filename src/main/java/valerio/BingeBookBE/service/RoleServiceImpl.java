package valerio.BingeBookBE.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import valerio.BingeBookBE.config.StringConfig;
import valerio.BingeBookBE.dto.RoleDTO;
import valerio.BingeBookBE.entity.Role;
import valerio.BingeBookBE.repositories.RoleDAO;
import valerio.BingeBookBE.service.interfaces.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
    final private RoleDAO roleDAO;

    @Autowired
    RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    public Role getRoleById(Long id) {
        return roleDAO.findById(id).orElseThrow(() -> new IllegalArgumentException(StringConfig.errorNotFoundRole + ": " + id));
    }

    @Override
    public Role getRoleByName(String name) {
        return roleDAO.findByName(name).orElseThrow(()-> new IllegalArgumentException(StringConfig.errorNotFoundRole + ": " + name));
    }

    @Override
    public List<Role> getAllRoles() {
        try {
            return roleDAO.findAll();
        } catch (Exception e) {
            throw new IllegalArgumentException(StringConfig.errorFailedToFetchRoles, e);
        }
    }

    @Override
    public void createRole(RoleDTO roleDTO) {
        if (roleDAO.existsByName(roleDTO.name())) {
            throw new IllegalArgumentException(StringConfig.errorAlreadyExistsRole + ": " + roleDTO.name());
        }
        
        Role role = new Role();
        role.setName(roleDTO.name().toUpperCase());

        roleDAO.save(role);
    }

    @Override
    public void updateRole(Long id, RoleDTO roleDTO) {
        if(roleDAO.existsById(id)){
            throw new IllegalArgumentException(StringConfig.errorAlreadyExistsRole + ": " + id);
        }

        if(roleDAO.existsByName(roleDTO.name())){
            throw new IllegalArgumentException(StringConfig.errorAlreadyExistsRole + ": " + roleDTO.name());
        }

        Role role = getRoleById(id);
        role.setName(roleDTO.name().toUpperCase());

        roleDAO.save(role);
    }

    @Override
    public void deleteRole(Long id) {
        if (!roleDAO.existsById(id)) {
            throw new IllegalArgumentException(StringConfig.errorNotFoundRole + ": " + id);
        }
        
        roleDAO.deleteById(id);
    }

    
}
