package com.alura_challenge.foro.repositories;

import com.alura_challenge.foro.models.authorization.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
}
