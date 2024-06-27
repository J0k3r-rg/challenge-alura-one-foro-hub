package com.alura_challenge.foro.utils;

import com.alura_challenge.foro.http.response.PermissionResponse;
import com.alura_challenge.foro.models.authorization.Permission;

public class PermissionUtils {

    public static PermissionResponse toPermissionResponse(Permission permission){
        return PermissionResponse.builder()
                .id(permission.getId())
                .name(permission.getName())
                .build();
    }

}
