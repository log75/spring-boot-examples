package com.wdka.security.security;

import com.wdka.security.model.Role;
import com.wdka.security.model.User;
import com.wdka.security.repository.RoleRepository;
import com.wdka.security.repository.UserRepository;
import com.wdka.security.security.role.UserPermission;
import com.wdka.security.security.role.UserRole;
import org.apache.catalina.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by alireza on 4/23/20.
 */
@Service
public class ApplicationUserService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public ApplicationUserService(UserRepository applicationUserRepository) {
        this.userRepository = applicationUserRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        String role = user.getRoles().stream().findFirst().get().getRoleName();

        List<SimpleGrantedAuthority> permissions=null;

        if (role.equals(UserRole.ADMINTRAINEE.name())) {
            permissions = UserRole.ADMINTRAINEE.getGrantedAuthority();
        }

        return new org.springframework.security.core.userdetails.User
                (user.getUsername(), user.getPassword(), user.getEnable(), true, true, true, permissions);
    }

}
