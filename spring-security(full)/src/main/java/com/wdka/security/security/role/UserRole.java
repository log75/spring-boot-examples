package com.wdka.security.security.role;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import static com.wdka.security.security.role.UserPermission.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by alireza on 4/20/20.
 */
public enum UserRole {

    STUDENT(Sets.newHashSet()), ADMIN(Sets.newHashSet(COURSE_READ, COURSE_WRITE, STUDENT_READ, STUDENT_WRITE)), ADMINTRAINEE(Sets.newHashSet(COURSE_READ, STUDENT_READ));

    private Set<UserPermission> permissions;

    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermission> getPermissions() {
        return permissions;
    }

    public List<SimpleGrantedAuthority> getGrantedAuthority() {
        Set<SimpleGrantedAuthority> permissions = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return new ArrayList<>(permissions);
    }

/*
// imperative

    public Set<GrantedAuthority> grantedAuthoritySet() {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (UserPermission userPermission : getPermissions()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(userPermission.getPermission()));
        }
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return grantedAuthorities;
    }
*/

}
