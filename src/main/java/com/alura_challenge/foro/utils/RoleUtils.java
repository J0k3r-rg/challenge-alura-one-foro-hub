package com.alura_challenge.foro.utils;

import com.alura_challenge.foro.http.response.RoleResponse;
import com.alura_challenge.foro.models.authorization.Role;

public class RoleUtils {

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

}
