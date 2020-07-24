package com.hellokoding.auth.controller;

import com.hellokoding.auth.model.User;
import com.hellokoding.auth.security.SecurityService;
import com.hellokoding.auth.repository.UserDao;
import com.hellokoding.auth.security.validator.UserValidator;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class UserController {

    private UserDao userDao;
    private SecurityService securityService;
    private UserValidator userValidator;
    private Logger logger;

    @Autowired
    public UserController(UserDao userDao, SecurityService securityService, UserValidator userValidator, Logger logger) {
        this.userDao = userDao;
        this.securityService = securityService;
        this.userValidator = userValidator;
        this.logger = logger;
    }

    @GetMapping("/registration")
    public String registration(Model model) throws InterruptedException {
        model.addAttribute("userForm", new User());
        System.out.println(logger.hashCode());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@Valid @ModelAttribute("userForm") User userForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        //userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/registration";
        }

        userDao.save(userForm);
        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());
        return "redirect:/welcome";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping({"/", "/welcome"})
    public String welcome() {
        return "welcome";
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("msg7", "Welcome to the Iran!");
    }

}
