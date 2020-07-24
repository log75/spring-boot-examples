package com.example.gitapi.service;

import com.example.gitapi.model.GitHubUser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Created by alireza on 7/10/20.
 */
@Service
public class GitHubApi {

    public GitHubUser getInfo(String username) {
        GitHubUser gitHubUser = new GitHubUser();
        try {
            HttpRequest request = HttpRequest.newBuilder().GET().uri(new URI("https://api.github.com/users/" + username)).build();
            HttpClient httpClient = HttpClient.newBuilder().build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if(response.statusCode()!=200){
                GitHubUser gitHubUser1 = new GitHubUser();
                gitHubUser1.setUsername("username not valid");
                return gitHubUser1;
            }
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response.body());
            gitHubUser.setAvatar(jsonNode.get("avatar_url").asText());
            gitHubUser.setUsername(jsonNode.get("name").asText());
            gitHubUser.setFollowers(jsonNode.get("followers").asInt());
            gitHubUser.setRepos(jsonNode.get("public_repos").asInt());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return gitHubUser;
    }

}
