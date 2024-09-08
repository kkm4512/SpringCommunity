package com.terror.springcommunity.constans;

import lombok.Getter;

@Getter
public enum UserRoleEnum {
    USER(UserRole.ROLE_USER),
    ADMIN(UserRole.ROLE_ADMIN);

    private final String role;

    UserRoleEnum(String role) {
        this.role = role;
    }

    @Getter
    public static class UserRole {
        public static final String ROLE_USER = "ROLE_USER";
        public static final String ROLE_ADMIN = "ROLE_ADMIN";
    }
}
