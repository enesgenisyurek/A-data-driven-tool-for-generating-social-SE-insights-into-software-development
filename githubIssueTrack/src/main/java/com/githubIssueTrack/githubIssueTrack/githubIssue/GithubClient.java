//package com.githubIssueTrack.githubIssueTrack.githubIssue;
//
//
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.client.RestTemplate;
//
//// Define a class to use the GitHub API
//public class GithubClient {
//
//    // Define the base URL for the API
//    private static final String BASE_URL = "https://api.github.com";
//
//    // Define the authentication token
//    private static final String TOKEN = "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";
//
//    // Define a method to list the issues for a given repository
//    public static Issue[] listIssues(String owner, String repo) {
//
//        // Create a RestTemplate object
//        RestTemplate restTemplate = new RestTemplate();
//
//        // Create a HttpHeaders object and set the authorization header
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Authorization", "token " + TOKEN);
//
//        // Create a HttpEntity object with the headers
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//
//        // Create the URL for the API endpoint
//        String url = BASE_URL + "/repos/" + owner + "/" + repo + "/issues";
//
//        // Make a GET request and get the response as an array of Issue objects
//        ResponseEntity<Issue[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, Issue[].class);
//
//        // Return the body of the response
//        return response.getBody();
//    }
//
//    // Define a method to create an issue for a given repository
//    public static Issue createIssue(String owner, String repo, Issue issue) {
//
//        // Create a RestTemplate object
//        RestTemplate restTemplate = new RestTemplate();
//
//        // Create a HttpHeaders object and set the authorization and content-type headers
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Authorization", "token " + TOKEN);
//        headers.set("Content-Type", "application/json");
//
//        // Create a HttpEntity object with the headers and the issue object
//        HttpEntity<Issue> entity = new HttpEntity<>(issue, headers);
//
//        // Create the URL for the API endpoint
//        String url = BASE_URL + "/repos/" + owner + "/" + repo + "/issues";
//
//        // Make a POST request and get the response as an Issue object
//        ResponseEntity<Issue> response = restTemplate.exchange(url, HttpMethod.POST, entity, Issue.class);
//
//        // Return the body of the response
//        return response.getBody();
//    }
//}
