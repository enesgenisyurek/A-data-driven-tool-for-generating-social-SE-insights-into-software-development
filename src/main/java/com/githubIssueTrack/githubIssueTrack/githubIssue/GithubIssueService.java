
package com.githubIssueTrack.githubIssueTrack.githubIssue;


import com.githubIssueTrack.githubIssueTrack.githubComment.GithubComment;
import com.githubIssueTrack.githubIssueTrack.githubComment.GithubCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;


@Service
public class GithubIssueService {

    @Value("${github.api.base-url}")
    private String githubApiBaseUrl;

    @Value("${github.auth.token}")
    private String authToken;

    @Value("${spring.codec.max-in-memory-size}")
    private String maxMemorySize;

    @Autowired
    private GithubCommentService githubCommentService;


//    public List<GithubIssue> getAllRepoIssues123(String owner, String repo) {
//        WebClient webClient = WebClient.builder()
//                .baseUrl(githubApiBaseUrl)
//                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + authToken)
//                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
//                .defaultHeader("X-GitHub-Api-Version", "2022-11-28")
//                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(1024 * 1024 * 500)) // Set maxInMemorySize to 500MB
//                .build();
//
//        List<GithubIssue> allIssues = new ArrayList<>();
//        int page = 1;
//        boolean hasNextPage = true;
//
//        while (hasNextPage) {
//            List<GithubIssue> issues = webClient.get()
//                    .uri("/repos/{owner}/{repo}/issues?page={page}&per_page=100&state=closed&comments-desc", owner, repo, page)
//                    .retrieve()
//                    .bodyToFlux(GithubIssue.class)  // Burada Issue sınıfını belirtiyoruz
//                    .collectList()
//                    .block();
//            if (issues == null || issues.isEmpty()) {
//                hasNextPage = false;
//            } else {
//                for (GithubIssue issue : issues) {
//                    List<GithubComment> comments = githubCommentService.getIssueComments(owner, repo, issue.getNumber());
//                    issue.setCommentList(comments);
//                }
//                allIssues.addAll(issues);
//                page++;
////                try {
////                    Thread.sleep(10); // 4 saniye bekle
////                } catch (InterruptedException e) {
////                    Thread.currentThread().interrupt();
////                }
//            }
//
//        }
//
//        return allIssues;
//    }


//
    public List<GithubIssue> getAllRepoIssues(String owner, String repo) {
        WebClient webClient = WebClient.builder()
                .baseUrl(githubApiBaseUrl)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + authToken)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("X-GitHub-Api-Version", "2022-11-28")
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(1024 * 1024 * 500))
                .build();

        List<GithubIssue> allIssues = new ArrayList<>();
        int page = 1;
        boolean hasNextPage = true;

        while (page<30) {
            try {
                List<GithubIssue> issues = webClient.get()
                        .uri("/repos/{owner}/{repo}/issues?page={page}&per_page=100&state=closed", owner, repo, page)
                        .retrieve()
                        .onStatus(HttpStatus::is4xxClientError, clientResponse ->
                                Mono.error(new RuntimeException("Client error: " + clientResponse.statusCode())))
                        .onStatus(HttpStatus::is5xxServerError, clientResponse ->
                                Mono.error(new RuntimeException("Server error: " + clientResponse.statusCode())))
                        .bodyToFlux(GithubIssue.class)
                        .collectList()
                        .block();

/*
https://github.com/microsoft/vscode/issues?page=2&q=is%3Aissue+is%3Aopen+sort%3Acomments-desc+label%3Abug
https://github.com/microsoft/vscode/issues?page=2&q=is%3Aissue+is%3Aclosed+sort%3Acomments-desc+label%3Abug
/repos/{owner}/{repo}/issues?page={page}&per_page=100&state=closed&"
/repos/{owner}/{repo}/issues?page={page}&per_page=100&q=is%3Aissue+is%3Aclosed+sort%3Acomments-desc+label%3Abug
 */
                if (issues == null || issues.isEmpty()) {
                    hasNextPage = false;
                } else {
                    for (GithubIssue issue : issues) {
                        List<GithubComment> comments = githubCommentService.getIssueComments(owner, repo, issue.getNumber());
                        issue.setCommentList(comments);

                    }
/*
                    for (GithubIssue issue : issues) {

                        githubIssue = new GithubIssue();
                        githubIssue.setId(issue.getId() == null ? 0 : issue.getId());
                        githubIssue.setNumber(issue.getNumber());
                        githubIssue.setUrl(issue.getUrl());
                        githubIssue.setTitle(issue.getTitle());
                        githubIssue.setComments_url(issue.getComments_url());
                        githubIssue.setState(issue.getState());
                        githubIssue.setLocked(issue.isLocked());
                        githubIssue.setComments(issue.getComments());
                        githubIssue.setCreated_at(issue.getCreated_at());
                        githubIssue.setUpdated_at(issue.getUpdated_at());
                        githubIssue.setClosed_at(issue.getClosed_at());
                        githubIssue.setAuthor_association(issue.getAuthor_association());
                        githubIssue.setActive_lock_reason(issue.getActive_lock_reason());
                        githubIssue.setDraft(issue.isDraft());



                        List<GithubComment> comments = githubCommentService.getIssueComments(owner, repo, issue.getNumber());
                        //issue.setCommentList(comments);
                        githubIssue.setCommentList(comments);
                        //issueRepository.save(githubIssue);
                    }*/
                    allIssues.addAll(issues);
                    page++;
                }
            } catch (Exception e) {
                // Handle exceptions, log error, etc.
                e.printStackTrace();
                break;
            }
        }
        return allIssues;
    }


//
//    public List<GithubIssue> getAllRepoIssues(String owner, String repo) {
//        WebClient webClient = WebClient.builder()
//                .baseUrl(githubApiBaseUrl)
//                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + authToken)
//                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
//                .defaultHeader("X-GitHub-Api-Version", "2022-11-28")
//                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(1024 * 1024 * 500))
//                .build();
//
//        List<GithubIssue> allIssues = new ArrayList<>();
//        int page = 200;
//        boolean hasNextPage = true;
//
//        while (page<220) {
//            try {
//                List<GithubIssue> issues = webClient.get()
//                        .uri("/repos/{owner}/{repo}/issues?page={page}&per_page=100", owner, repo, page)
//                        .retrieve()
//                        .onStatus(HttpStatus::is4xxClientError, clientResponse ->
//                                Mono.error(new RuntimeException("Client error: " + clientResponse.statusCode())))
//                        .onStatus(HttpStatus::is5xxServerError, clientResponse ->
//                                Mono.error(new RuntimeException("Server error: " + clientResponse.statusCode())))
//                        .bodyToFlux(GithubIssue.class)
//                        .collectList()
//                        .block();
//
///*
//https://github.com/microsoft/vscode/issues?page=2&q=is%3Aissue+is%3Aopen+sort%3Acomments-desc+label%3Abug
//https://github.com/microsoft/vscode/issues?page=2&q=is%3Aissue+is%3Aclosed+sort%3Acomments-desc+label%3Abug
///repos/{owner}/{repo}/issues?page={page}&per_page=100&state=closed&"
///repos/{owner}/{repo}/issues?page={page}&per_page=100&q=is%3Aissue+is%3Aclosed+sort%3Acomments-desc+label%3Abug
// */
//
//                    for (GithubIssue issue : issues) {
//                        System.out.println("issues-test");
//                        List<GithubComment> comments = githubCommentService.getIssueComments(owner, repo, issue.getNumber());
//                        issue.setCommentList(comments);
//
//                    }
///*
//                    for (GithubIssue issue : issues) {
//
//                        githubIssue = new GithubIssue();
//                        githubIssue.setId(issue.getId() == null ? 0 : issue.getId());
//                        githubIssue.setNumber(issue.getNumber());
//                        githubIssue.setUrl(issue.getUrl());
//                        githubIssue.setTitle(issue.getTitle());
//                        githubIssue.setComments_url(issue.getComments_url());
//                        githubIssue.setState(issue.getState());
//                        githubIssue.setLocked(issue.isLocked());
//                        githubIssue.setComments(issue.getComments());
//                        githubIssue.setCreated_at(issue.getCreated_at());
//                        githubIssue.setUpdated_at(issue.getUpdated_at());
//                        githubIssue.setClosed_at(issue.getClosed_at());
//                        githubIssue.setAuthor_association(issue.getAuthor_association());
//                        githubIssue.setActive_lock_reason(issue.getActive_lock_reason());
//                        githubIssue.setDraft(issue.isDraft());
//
//
//
//                        List<GithubComment> comments = githubCommentService.getIssueComments(owner, repo, issue.getNumber());
//                        //issue.setCommentList(comments);
//                        githubIssue.setCommentList(comments);
//                        //issueRepository.save(githubIssue);
//                    }*/
//                    allIssues.addAll(issues);
//                    page++;
//
//            } catch (Exception e) {
//                // Handle exceptions, log error, etc.
//                e.printStackTrace();
//                break;
//            }
//        }
//        return allIssues;
//    }
//
//}

//    public List<GithubIssue> getAllRepoIssues(String owner, String repo) {
//        WebClient webClient = WebClient.builder()
//                .baseUrl(githubApiBaseUrl)
//                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + authToken)
//                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
//                .defaultHeader("X-GitHub-Api-Version", "2022-11-28")
//                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(1024 * 1024 * 500))
//                .build();
//
//        List<GithubIssue> allIssues = new ArrayList<>();
//        int page = 1;
//
//        while (true) {
//            try {
//                int finalPage = page;
//                ResponseEntity<List<GithubIssue>> response = webClient.get()
//                        .uri(uriBuilder -> uriBuilder.path("/repos/{owner}/{repo}/issues")
//                                .queryParam("page", finalPage)
//                                .queryParam("per_page", 100)
//                                .queryParam("state", "closed")
//                                .build(owner, repo))
//                        .retrieve()
//                        .onStatus(HttpStatus::is4xxClientError, clientResponse ->
//                                Mono.error(new RuntimeException("Client error: " + clientResponse.statusCode())))
//                        .onStatus(HttpStatus::is5xxServerError, clientResponse ->
//                                Mono.error(new RuntimeException("Server error: " + clientResponse.statusCode())))
//                        .toEntityList(GithubIssue.class)
//                        .block();
//
//                List<GithubIssue> issues = response.getBody();
//                if (issues == null || issues.isEmpty()) {
//                    break; // Döngüyü sonlandır
//                }
//
//                for (GithubIssue issue : issues) {
//                    List<GithubComment> comments = githubCommentService.getIssueComments(owner, repo, issue.getNumber());
//                    issue.setCommentList(comments);
//                }
//
//                allIssues.addAll(issues);
//                page++;
//            } catch (HttpClientErrorException.Forbidden e) {
//                // Handle 403 Forbidden error (Rate Limit)
//                HttpHeaders headers = e.getResponseHeaders();
//                int rateLimitRemaining = Integer.parseInt(headers.getFirst("X-RateLimit-Remaining"));
//                int rateLimitReset = Integer.parseInt(headers.getFirst("X-RateLimit-Reset"));
//
//                Instant resetTime = Instant.ofEpochSecond(rateLimitReset);
//                Duration waitTime = Duration.between(Instant.now(), resetTime).plusHours(1); // 1 saatlik bekleme süresi
//                System.out.println("Rate limit reached. Waiting for " + waitTime.toHours() + " hours.");
//                try {
//                    Thread.sleep(waitTime.toMillis());
//                } catch (InterruptedException ex) {
//                    ex.printStackTrace();
//                }
//            } catch (Exception e) {
//                // Handle other exceptions
//                e.printStackTrace();
//                break;
//            }
//        }
//        return allIssues;
//    }

}