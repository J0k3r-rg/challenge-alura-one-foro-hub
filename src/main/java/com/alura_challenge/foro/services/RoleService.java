package com.alura_challenge.foro.services;

import com.alura_challenge.foro.utils.RoleUtils;
import com.alura_challenge.foro.http.request.RoleRequest;
import com.alura_challenge.foro.http.response.RoleResponse;
import com.alura_challenge.foro.models.authorization.Permission;
import com.alura_challenge.foro.models.authorization.Role;
import com.alura_challenge.foro.repositories.PermissionRepository;
import com.alura_challenge.foro.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    public RoleResponse createRole(RoleRequest roleRequest){
        List<Permission> permissions = new ArrayList<>();
        roleRequest.getPermissions().forEach( permissionRequest ->
                permissionRepository.findById(permissionRequest.getId()).ifPresent(permissions::add)
        );
        Role role = roleRepository.save(Role.builder()
                        .name(roleRequest.getName())
                        .permissions(permissions)
                .build());
        return RoleUtils.toRoleResponse(role);
    }


}
