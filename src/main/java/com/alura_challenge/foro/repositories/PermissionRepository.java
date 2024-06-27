package com.alura_challenge.foro.repositories;

import com.alura_challenge.foro.models.authorization.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, String> {
}
