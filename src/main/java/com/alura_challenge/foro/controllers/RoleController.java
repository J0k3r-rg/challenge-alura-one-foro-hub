package com.alura_challenge.foro.controllers;

import com.alura_challenge.foro.exceptions.RoleException;
import com.alura_challenge.foro.http.request.RoleRequest;
import com.alura_challenge.foro.services.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PreAuthorize("hasAuthority('Create_role')")
    @PostMapping
    public ResponseEntity<?> createRole(@RequestBody @Valid RoleRequest roleRequest){
        return ResponseEntity.ok(roleService.createRole(roleRequest));
    }

    @PreAuthorize("hasAuthority('Update_role')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateRole(@PathVariable String id, @RequestBody @Valid RoleRequest roleRequest) throws RoleException {
        return ResponseEntity.ok(roleService.updateRole(id, roleRequest));
    }

    @PreAuthorize("hasAuthority('Delete_role')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable String id) throws RoleException {
        return ResponseEntity.ok(roleService.deleteRole(id));
    }

    @PreAuthorize("hasAuthority('Read_role')")
    @GetMapping
    public ResponseEntity<?> getAllRoles(){
        return ResponseEntity.ok(roleService.getAllRoles());
    }

    @PreAuthorize("hasAuthority('Read_role')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getRoleById(@PathVariable String id) throws RoleException {
        return ResponseEntity.ok(roleService.getRoleByID(id));
    }

}
