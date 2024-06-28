package com.alura_challenge.foro.http.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PermissionRequest {

    @NotBlank
    private String name;

}
