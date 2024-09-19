package br.com.devbean.mode02.dtos;

import br.com.devbean.mode02.enums.UserRole;
import jakarta.validation.constraints.NotNull;

public record LoginRegisterRequestDTO(
        @NotNull String email,
        @NotNull String password,
        @NotNull UserRole role)
{ }
