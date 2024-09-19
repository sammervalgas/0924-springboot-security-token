package br.com.devbean.mode02.dtos;

import jakarta.validation.constraints.NotNull;

public record LoginAuthRequestDTO(
        @NotNull String email,
        @NotNull String password) {

}
