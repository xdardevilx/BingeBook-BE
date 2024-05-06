package valerio.BingeBookBE.controllers;


import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import valerio.BingeBookBE.dto.RoleDTO;
import valerio.BingeBookBE.entity.Role;
import valerio.BingeBookBE.service.RoleService;

import java.math.BigInteger;

@RestController
@RequestMapping("/roles")
public class RoleController {
    private final RoleService roleService;

    private RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createRole(RoleDTO roleDTO) {
        Role role = roleService.createRole(roleDTO);
        return new ResponseEntity<Role>(role, HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public Page<Role> getListRoles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        return roleService.getListRoles(page, size, sortBy);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateRole(BigInteger id, RoleDTO roleDTO) {
        Role role = roleService.updateRole(id, roleDTO);
        return new ResponseEntity<Role>(role, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public void deleteTag(BigInteger id) {
        roleService.deleteRole(id);
    }

}
