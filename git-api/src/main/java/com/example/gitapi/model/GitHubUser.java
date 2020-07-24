package com.example.gitapi.model;

/**
 * Created by alireza on 7/10/20.
 */
public class GitHubUser {

    private String avatar;
    private String username;
    private int followers;
    private int repos;

    public GitHubUser() {
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public int getRepos() {
        return repos;
    }

    public void setRepos(int repos) {
        this.repos = repos;
    }
}
