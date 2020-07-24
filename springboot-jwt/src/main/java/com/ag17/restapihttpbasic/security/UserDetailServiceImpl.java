package com.ag17.restapihttpbasic.security;


import com.ag17.restapihttpbasic.model.Permission;
import com.ag17.restapihttpbasic.model.Role;
import com.ag17.restapihttpbasic.model.User;
import com.ag17.restapihttpbasic.repository.RoleRepository;
import com.ag17.restapihttpbasic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public UserDetailServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(user.getRoles().stream().findFirst().get().getName());

        if (user == null) throw new UsernameNotFoundException(username);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role r : user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + r.getName()));
        }

        for (Permission permission : role.getPermissions()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(permission.getName()));
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, grantedAuthorities);
    }
}
