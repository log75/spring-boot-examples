package com.wdka.security.security;

import com.wdka.security.security.jwt.JwtTokenVerifier;
import com.wdka.security.security.jwt.JwtUsernamePasswordAuthenticationFilter;
import com.wdka.security.security.rememberme.PersistentTokenDaoImp;
import com.wdka.security.security.rememberme.RememberMeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

/**
 * Created by alireza on 4/19/20.
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private PasswordEncoder passwordEncoder;
    private ApplicationUserService applicationUserService;
    private PersistentTokenRepository persistentTokenRepository;

    @Autowired
    public SecurityConfig(PasswordEncoder passwordEncoder, ApplicationUserService applicationUserService, PersistentTokenRepository persistentTokenRepository) {
        this.passwordEncoder = passwordEncoder;
        this.applicationUserService = applicationUserService;
        this.persistentTokenRepository = persistentTokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and()
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/webjars/**", "/static", "/register","/error").permitAll()
                .anyRequest()
                .authenticated()
                .and().formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/courses", true).and()
                .logout().invalidateHttpSession(true).deleteCookies("JSESSIONID").permitAll().and()
                .rememberMe().rememberMeServices(rememberMeServices());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(applicationUserService).passwordEncoder(passwordEncoder);
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(applicationUserService);
        return daoAuthenticationProvider;
    }

    @Bean
    public RememberMeServices rememberMeServices() {
        return new RememberMeServices("remember-me", applicationUserService, (PersistentTokenDaoImp) persistentTokenRepository);
    }
}