package com.instagram.InstagramAnalyticsDashboard.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.instagram.InstagramAnalyticsDashboard.entity.InstagramAccount;
import com.instagram.InstagramAnalyticsDashboard.repository.InstagramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class InstagramService {

    @Autowired
    private InstagramRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${instagram.business.id}")
    private String businessId;

    @Value("${instagram.page.access.token}")
    private String accessToken;

    
    private final ObjectMapper mapper = new ObjectMapper();

    // Fetch Raw JSON from Instagram Graph API
    public String getProfile() {

        String url = "https://graph.facebook.com/v23.0/"
                + businessId
                + "?fields=id,username,biography,followers_count,follows_count,media_count,profile_picture_url"
                + "&access_token="
                + accessToken;

        return restTemplate.getForObject(url, String.class);
    }

    // Return Clean Response
    public InstagramAccount fetchProfile() throws Exception {

        String response = getProfile();

        JsonNode json = mapper.readTree(response);

        InstagramAccount account = new InstagramAccount();

        account.setInstagramId(json.path("id").asText());
        account.setUsername(json.path("username").asText());
        account.setBiography(json.path("biography").asText());

        account.setFollowersCount(json.path("followers_count").asInt());
        account.setFollowingCount(json.path("follows_count").asInt());
        account.setPostCount(json.path("media_count").asInt());

        account.setProfilePicture(json.path("profile_picture_url").asText());

        return account;
    }

    public String saveProfile() throws Exception {

    System.out.println("===== SAVE PROFILE CALLED =====");

    String response = getProfile();

    JsonNode json = mapper.readTree(response);

    InstagramAccount account = repository.findAll()
            .stream()
            .findFirst()
            .orElse(new InstagramAccount());

    account.setInstagramId(json.path("id").asText());
    account.setUsername(json.path("username").asText());
    account.setBiography(json.path("biography").asText());

    account.setFollowersCount(json.path("followers_count").asInt());
    account.setFollowingCount(json.path("follows_count").asInt());

    // JSON me media_count hai, entity me postCount
    account.setPostCount(json.path("media_count").asInt());

    account.setProfilePicture(json.path("profile_picture_url").asText());

    System.out.println(account);

    repository.save(account);

    System.out.println("===== SAVED =====");

    return "Profile Saved Successfully";
}
}