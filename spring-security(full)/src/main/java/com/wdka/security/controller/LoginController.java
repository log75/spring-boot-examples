package com.wdka.security.controller;

import com.wdka.security.model.User;
import com.wdka.security.repository.UserRepository;
import com.wdka.security.repository.UserDao;
import com.wdka.security.security.ApplicationUserService;
import com.wdka.security.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by alireza on 4/20/20.
 */

@Controller
public class LoginController {

    private UserDao userDao;
    private UserRepository applicationUserRepository;
    private SecurityService securityService;
    private DaoAuthenticationProvider daoAuthenticationProvider;
    private ApplicationUserService applicationUserService;

    @Autowired
    public LoginController(UserDao userDao, UserRepository applicationUserRepository, SecurityService securityService, DaoAuthenticationProvider daoAuthenticationProvider, ApplicationUserService applicationUserService) {
        this.userDao = userDao;
        this.applicationUserRepository = applicationUserRepository;
        this.securityService = securityService;
        this.daoAuthenticationProvider = daoAuthenticationProvider;
        this.applicationUserService = applicationUserService;
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String showSignUpPage(@ModelAttribute("message") String message) {
        return "signup";
    }

    @PostMapping("/register")
    public String submit(@Valid @ModelAttribute User user, BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("message", "incorrect data input");
            return "redirect:/register";
        }

        if (applicationUserRepository.findByUsername(user.getUsername()) != null) {
            redirectAttributes.addFlashAttribute("message", "user name already exist");
            return "redirect:/register";
        } else {
            userDao.saveUser(user);
        }

        try {
            securityService.autoLogin(user.getUsername(), user.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/courses";
    }


    @GetMapping("/courses")
    public String showCoursesPage() {
        return "courses";
    }


}