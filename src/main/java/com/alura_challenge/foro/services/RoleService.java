package com.alura_challenge.foro.services;

import com.alura_challenge.foro.exceptions.RoleException;
import com.alura_challenge.foro.utils.RoleUtils;
import com.alura_challenge.foro.http.request.RoleRequest;
import com.alura_challenge.foro.http.response.RoleResponse;
import com.alura_challenge.foro.models.authorization.Permission;
import com.alura_challenge.foro.models.authorization.Role;
import com.alura_challenge.foro.repositories.PermissionRepository;
import com.alura_challenge.foro.repositories.RoleRepository;
import jakarta.transaction.Transactional;
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

    @Transactional
    public RoleResponse createRole(RoleRequest roleRequest){
        List<Permission> permissions = new ArrayList<>();
        roleRequest.getPermissions().forEach( permissionRequest ->
                permissionRepository.findByNameAndEnable(permissionRequest.getName(),true).ifPresent(permissions::add)
        );
        Role role = roleRepository.save(Role.builder()
                        .name(roleRequest.getName())
                        .permissions(permissions)
                .build());
        return RoleUtils.toRoleResponse(role);
    }

    @Transactional
    public RoleResponse updateRole(String id, RoleRequest roleRequest) throws RoleException {
        Role role = roleRepository.findById(id).orElseThrow(
                () -> new RoleException("Role not found")
        );
        if(!role.isEnable())
            throw new RoleException("Role is not enable");
        List<Permission> permissions = new ArrayList<>();
        roleRequest.getPermissions().forEach( permissionRequest ->
                permissionRepository.findByNameAndEnable(permissionRequest.getName(),true).ifPresent(permissions::add)
        );
        role.setName(roleRequest.getName());
        role.setPermissions(permissions);
        roleRepository.save(role);
        return RoleUtils.toRoleResponse(role);
    }

    @Transactional
    public RoleResponse deleteRole(String id) throws RoleException {
        Role role = roleRepository.findById(id).orElseThrow(
                () -> new RoleException("Role not found")
        );
        if(!role.isEnable())
            throw new RoleException("Role is not enable");
        role.setEnable(false);
        roleRepository.save(role);
        return RoleUtils.toRoleResponse(role);
    }

    public RoleResponse getRoleByID(String id) throws RoleException {
        Role role = roleRepository.findById(id).orElseThrow(
                () -> new RoleException("Role not found")
        );
        if(!role.isEnable())
            throw new RoleException("Role is not enable");
        return RoleUtils.toRoleResponse(role);
    }

    public List<RoleResponse> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        List<RoleResponse> roleResponses = new ArrayList<>();
        roles.forEach(role -> {
            if(role.isEnable())
                roleResponses.add(RoleUtils.toRoleResponse(role));
        });
        return roleResponses;
    }


}
