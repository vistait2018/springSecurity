package com.example.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    STUDENT( Sets.newHashSet()),
    ADMIN( Sets.newHashSet(COURSE_READ,COURSE_WRITE,STUDENT_READ,STUDENT_WRITE)),
    ADMINTRAINEE( Sets.newHashSet(COURSE_READ,STUDENT_READ));

    ApplicationUserRole(Set<ApplicationUserPermission> permission) {
        this.permission = permission;
    }

    private final Set<ApplicationUserPermission> permission;

    public Set<ApplicationUserPermission> getPermission() {
        return permission;
    }

    public Set<SimpleGrantedAuthority> grantedAuthorities(){
        Set<SimpleGrantedAuthority> permissions = getPermission().stream()
                .map(permission-> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return permissions;
    }



}
