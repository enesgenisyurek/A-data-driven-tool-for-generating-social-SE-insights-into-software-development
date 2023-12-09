package com.githubIssueTrack.githubIssueTrack.githubComment;

import com.githubIssueTrack.githubIssueTrack.githubIssue.GithubIssue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class GithubCommentService {

    @Value("${github.api.base-url}")
    private String githubApiBaseUrl;

    @Value("${github.auth.token}")
    private String authToken;

    public List<GithubComment> getIssueComments(String owner, String repo, int issueNumber) {
        WebClient webClient = WebClient.builder()
                .baseUrl(githubApiBaseUrl)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + authToken)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("X-GitHub-Api-Version", "2022-11-28")
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(1024 * 1024))
                .build();

        List<GithubComment> allComments = new ArrayList<>();
        int page = 1;
        boolean hasNextPage = true;

        while (hasNextPage) {
            List<GithubComment> comments = webClient.get()
                    .uri("/repos/{owner}/{repo}/issues/{issueNumber}/comments?page={page}&per_page=100",
                            owner, repo, issueNumber, page)
                    .retrieve()
                    .bodyToFlux(GithubComment.class)
                    .collectList()
                    .block();

            if (comments == null || comments.isEmpty()) {
                hasNextPage = false;
            } else {
                allComments.addAll(comments);
                page++;
            }
        }

        return allComments;
    }
}
