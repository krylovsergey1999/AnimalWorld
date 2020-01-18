package ru.animal.world.utils;

import org.springframework.security.core.GrantedAuthority;

public enum  Role implements GrantedAuthority {
    USER, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
