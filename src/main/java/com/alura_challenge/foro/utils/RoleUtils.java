package com.alura_challenge.foro.utils;

import com.alura_challenge.foro.http.request.RoleRequest;
import com.alura_challenge.foro.http.response.RoleResponse;
import com.alura_challenge.foro.models.authorization.Permission;
import com.alura_challenge.foro.models.authorization.Role;
import com.alura_challenge.foro.repositories.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class RoleUtils {

    @Autowired
    private static PermissionRepository permissionRepository;

    public static RoleResponse toRoleResponse(Role role){
        return RoleResponse.builder()
                .name(role.getName())
                .permissions(
                        role.getPermissions().stream()
                                .map(PermissionUtils::toPermissionResponse)
                                .toList()
                )
                .build();
    }

    public static Role toRole(RoleRequest roleRequest){
        List<Permission> permissions = new ArrayList<>();
        roleRequest.getPermissions().forEach( permissionRequest ->
                permissionRepository.findByNameAndEnable(permissionRequest.getName(),true).ifPresent(permissions::add)
        );
        return Role.builder()
                .name(roleRequest.getName())
                .permissions(permissions)
                .build();
    }

}
