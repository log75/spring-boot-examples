package com.wdka.security.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * Created by alireza on 4/27/20.
 */
@Service
public class SecurityService {

    private ApplicationUserService applicationUserService;
    private DaoAuthenticationProvider daoAuthenticationProvider;

    @Autowired
    public SecurityService(ApplicationUserService applicationUserService, DaoAuthenticationProvider daoAuthenticationProvider) {
        this.applicationUserService = applicationUserService;
        this.daoAuthenticationProvider = daoAuthenticationProvider;
    }

    public String findLoggedInUsername() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (userDetails instanceof UserDetails) {
            return ((UserDetails) userDetails).getUsername();
        }

        return null;
    }


    public void autoLogin(final String username, final String password) {
        UserDetails userDetails = applicationUserService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(userDetails, userDetails, userDetails.getAuthorities());

        try {
            daoAuthenticationProvider.authenticate(authRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (authRequest.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authRequest);
        }
    }

}