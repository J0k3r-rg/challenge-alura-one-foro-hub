package com.alura_challenge.foro.services;

import com.alura_challenge.foro.exceptions.PermissionException;
import com.alura_challenge.foro.http.request.PermissionRequest;
import com.alura_challenge.foro.http.response.PermissionResponse;
import com.alura_challenge.foro.models.authorization.Permission;
import com.alura_challenge.foro.repositories.PermissionRepository;
import com.alura_challenge.foro.utils.PermissionUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Transactional
    public PermissionResponse savePermission(PermissionRequest permissionRequest){
        Permission permission = permissionRepository.save(PermissionUtils.toPermission(permissionRequest));
        return PermissionUtils.toPermissionResponse(permission);
    }

    public PermissionResponse getPermission(String id) throws PermissionException {
        Permission permission = permissionRepository.findById(id).orElse(null);
        if(permission == null){
            throw new PermissionException("Permission not found");
        }
        return PermissionUtils.toPermissionResponse(permission);
    }

    public List<Permission> getAllPermissions(){
        return permissionRepository.findByEnable(true);
    }

    @Transactional
    public void deletePermission(String id) throws PermissionException {
        Permission permission = permissionRepository.findById(id).orElseThrow(() -> new PermissionException("Permission not found"));
        permission.setEnable(false);
    }

    @Transactional
    public PermissionResponse updatePermission(String id, PermissionRequest permissionRequest){
        Permission permission = permissionRepository.findById(id).orElseThrow(() -> new RuntimeException("Permission not found"));
        permission.setName(permissionRequest.getName());
        return PermissionUtils.toPermissionResponse(permission);
    }

}
