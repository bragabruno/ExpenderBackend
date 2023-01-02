package com.bragdev.expenderbackend.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import GoogleClient;

import java.util.Arrays;

@RestController
public class AuthenticationController extends BaseController {
    @Value("${google.client.id}")
    private String googleClientId;

    @Value("${google.client.secret}")
    private String googleClientSecret;

    @Value("${google.developer.key}")
    private String googleDeveloperKey;

    private GoogleClient googleClient;

    private void setup() {
        googleClient = new GoogleClient();
        googleClient.setClientId(googleClientId);
        googleClient.setClientSecret(googleClientSecret);
        googleClient.setRedirectUri("http://localhost:8080/authentication/oauth");
        googleClient.setDeveloperKey(googleDeveloperKey);
        googleClient.setApprovalPrompt("auto");
        googleClient.setScopes(Arrays.asList("https://www.googleapis.com/auth/userinfo.email", "https://www.googleapis.com/auth/userinfo.profile"));
    }
}
