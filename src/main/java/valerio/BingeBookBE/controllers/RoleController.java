package valerio.BingeBookBE.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import valerio.BingeBookBE.dto.RoleDTO;
import valerio.BingeBookBE.service.RoleServiceImpl;
import valerio.BingeBookBE.utils.ResponseEntityCustom;

@RestController
@RequestMapping("/roles")
public class RoleController {
    private final RoleServiceImpl roleService;

    @Autowired
    private RoleController(RoleServiceImpl roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getRoleById(@PathVariable Long id) {
        return ResponseEntityCustom.responseSuccess(this.roleService.getRoleById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getListRoles() {
        return ResponseEntityCustom.responseSuccess(this.roleService.getAllRoles(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createRole(@Validated @RequestBody RoleDTO roleDTO) {
        this.roleService.createRole(roleDTO);
        return ResponseEntityCustom.responseSuccess("Role created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateRole(@PathVariable Long id, @Validated @RequestBody RoleDTO roleDTO) {
        this.roleService.updateRole(id, roleDTO);
        return ResponseEntityCustom.responseSuccess("Role updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTag(@PathVariable Long id) {
        this.roleService.deleteRole(id);
        return ResponseEntityCustom.responseSuccess("Role deleted successfully", HttpStatus.OK);
    }

}
