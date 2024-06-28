package com.alura_challenge.foro.utils;

import com.alura_challenge.foro.http.request.PermissionRequest;
import com.alura_challenge.foro.http.response.PermissionResponse;
import com.alura_challenge.foro.models.authorization.Permission;

public class PermissionUtils {

    public static PermissionResponse toPermissionResponse(Permission permission){
        return PermissionResponse.builder()
                .id(permission.getId())
                .name(permission.getName())
                .build();
    }

    public static Permission toPermission(PermissionRequest permissionRequest){
        return Permission.builder()
                .name(permissionRequest.getName())
                .build();
    }

}
