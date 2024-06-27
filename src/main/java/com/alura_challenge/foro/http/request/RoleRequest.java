package com.alura_challenge.foro.http.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class RoleRequest {

    @NotBlank
    private String name;

    @Valid
    @NotNull
    private List<PermissionRequest> permissions;

}
