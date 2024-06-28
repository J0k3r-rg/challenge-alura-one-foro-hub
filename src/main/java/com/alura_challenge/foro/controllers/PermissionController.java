package com.alura_challenge.foro.controllers;

import com.alura_challenge.foro.exceptions.PermissionException;
import com.alura_challenge.foro.http.request.PermissionRequest;
import com.alura_challenge.foro.services.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @PreAuthorize("hasAuthority('Read_permission')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getPermissionById(@PathVariable String id) throws PermissionException {
        return ResponseEntity.ok(permissionService.getPermission(id));
    }

    @PreAuthorize("hasAuthority('Read_permission')")
    @GetMapping
    public ResponseEntity<?> getAllPermissions() {
        return ResponseEntity.ok(permissionService.getAllPermissions());
    }

    @PreAuthorize("hasAuthority('Create_permission')")
    @PostMapping
    public ResponseEntity<?> createPermission(@RequestBody PermissionRequest permission) {
        return ResponseEntity.ok(permissionService.savePermission(permission));
    }

    @PreAuthorize("hasAuthority('Update_permission')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePermission(@PathVariable String id, @RequestBody PermissionRequest permission) {
        return ResponseEntity.ok(permissionService.updatePermission(id, permission));
    }

    @PreAuthorize("hasAuthority('Delete_permission')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePermission(@PathVariable String id) throws PermissionException {
        permissionService.deletePermission(id);
        return ResponseEntity.ok().build();
    }

}
