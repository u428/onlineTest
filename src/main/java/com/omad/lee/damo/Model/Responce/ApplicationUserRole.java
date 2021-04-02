package com.omad.lee.damo.Model.Responce;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;


public enum ApplicationUserRole {
    USER(Sets.newHashSet()),

    ADMIN(Sets.newHashSet(ApplicationUserPermission.USER_READ,
            ApplicationUserPermission.USER_WRITE,
            ApplicationUserPermission.QUESTION_READ,
            ApplicationUserPermission.QUESTION_WRITE)),

    ADMINTRAINEE(Sets.newHashSet(ApplicationUserPermission.USER_READ,
            ApplicationUserPermission.QUESTION_READ));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public Set<String> getGrantedAuthorities() {
        Set<String> permissions = getPermissions().stream()
                .map(permission ->permission.getPermission())
                .collect(Collectors.toSet());
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities2() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
