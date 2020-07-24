package com.ag17.restapihttpbasic.auth;

import com.ag17.restapihttpbasic.model.UsernamePasswordRequest;
import com.ag17.restapihttpbasic.model.Role;
import com.ag17.restapihttpbasic.model.User;
import com.ag17.restapihttpbasic.repository.RoleRepository;
import com.ag17.restapihttpbasic.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by alireza on 6/19/20.
 */
@RestController
@RequestMapping(value = "/api/auth")
public class Auth {

    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;
    private UserRepository userRepository;

    public Auth(PasswordEncoder passwordEncoder, RoleRepository roleRepository, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/signUp")
    public User signUpUser(@RequestBody UsernamePasswordRequest usernamePasswordRequest) {
        User user = new User();
        user.setUsername(usernamePasswordRequest.getUsername());
        user.setPassword(passwordEncoder.encode(usernamePasswordRequest.getPassword()));
        user.setEnabled(true);
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName("USER"));
        user.setRoles(roles);
        User savedUser = userRepository.save(user);
        return savedUser;
    }

}
