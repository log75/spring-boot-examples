package com.example.gitapi.controller;

import com.example.gitapi.config.MailConfig;
import com.example.gitapi.event.PostMethodCallEvent;
import com.example.gitapi.model.GitHubUser;
import com.example.gitapi.service.GitHubApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by alireza on 7/10/20.
 */
@Controller
@RequestMapping(value = "/")
public class IndexController {

    Logger logger = LoggerFactory.getLogger(IndexController.class.getName());
    private MailConfig mailConfig;
    private GitHubApi gitHubApi;
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public IndexController(MailConfig mailConfig, GitHubApi gitHubApi, ApplicationEventPublisher applicationEventPublisher) {
        this.mailConfig = mailConfig;
        this.gitHubApi = gitHubApi;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @GetMapping
    public ModelAndView getIndex(@ModelAttribute("info") GitHubUser gitHubUser) {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("info", gitHubUser);
        return modelAndView;
    }

    @PostMapping
    public String postUsername(@RequestParam("username") String username, RedirectAttributes redirectAttributes) {
        GitHubUser info = gitHubApi.getInfo(username);
        redirectAttributes.addFlashAttribute("info", info);
        applicationEventPublisher.publishEvent(new PostMethodCallEvent(this, "post method call"));
        return "redirect:/";
    }


    @PostMapping("/message")
    @ResponseBody
    public GitHubUser g(@RequestParam("username") String username) {
        GitHubUser gitHubUser = new GitHubUser();
        gitHubUser.setUsername(username);
        gitHubUser.setRepos(3);
        return gitHubUser;
    }

}
