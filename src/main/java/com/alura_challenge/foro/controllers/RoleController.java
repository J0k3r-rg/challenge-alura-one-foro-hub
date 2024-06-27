package com.alura_challenge.foro.controllers;

import com.alura_challenge.foro.http.request.RoleRequest;
import com.alura_challenge.foro.services.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PreAuthorize("hasAuthority('Create_role')")
    @PostMapping
    public ResponseEntity<?> createRole(@RequestBody RoleRequest roleRequest){
        return ResponseEntity.ok("success");
//        return ResponseEntity.ok(roleService.createRole(roleRequest));
    }

}
