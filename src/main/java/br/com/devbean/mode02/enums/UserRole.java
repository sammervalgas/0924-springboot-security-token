package br.com.devbean.mode02.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRole {
    ADMIN("ROLE_ADMIN"),
    GUEST("ROLE_GUEST");

    private String role;
}
