//package com.githubIssueTrack.githubIssueTrack.githubIssue;
//
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//@Service
//public class GithubIssueService {
//
//    private final String BASE_URL = "https://api.github.com";
//
//    public String getRepoIssue(String owner, String repo){
//       // String apiUrl = BASE_URL + "/repos/{owner}/{repo}/issues";
//        String apiUrl = BASE_URL + "/repos/{owner}/{repo}/issues?per_page=100&page=1";
//
//        RestTemplate restTemplate = new RestTemplate();
//        String result = restTemplate.getForObject(apiUrl, String.class, owner, repo);
//
//        return result;
//    }
//
//}


// bu çalışıyor



//bu çalışan bir kod
package com.githubIssueTrack.githubIssueTrack.githubIssue;
//
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
//
//@Service
//public class GithubIssueService {
//
//    @Value("${github.api.base-url}")
//    private String githubApiBaseUrl;
//
//    @Value("${github.auth.token}")
//    private String authToken;
//
//    public String getRepoIssues(String owner, String repo) {
//        WebClient webClient = WebClient.builder()
//                .baseUrl(githubApiBaseUrl)
//                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + authToken)
//                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
//                .defaultHeader("X-GitHub-Api-Version", "2022-11-28")
//                .build();
//
//        return webClient.get()
//                .uri("/repos/{owner}/{repo}/issues", owner, repo)
//                .retrieve()
//                .bodyToMono(String.class)
//                .block();
//    }
//
//}

//
@Service
public class GithubIssueService {

    @Value("${github.api.base-url}")
    private String githubApiBaseUrl;

    @Value("${github.auth.token}")
    private String authToken;



    public List<GithubIssue> getAllRepoIssues123(String owner, String repo) {
        WebClient webClient = WebClient.builder()
                .baseUrl(githubApiBaseUrl)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + authToken)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("X-GitHub-Api-Version", "2022-11-28")
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(1024 * 1024)) // Set maxInMemorySize to 1MB
                .build();

        List<GithubIssue> allIssues = new ArrayList<>();
        int page = 1;
        boolean hasNextPage = true;





        while (hasNextPage) {
            List<GithubIssue> issues = webClient.get()
                    .uri("/repos/{owner}/{repo}/issues?page={page}&per_page=100", owner, repo, page)
                    .retrieve()
                    .bodyToFlux(GithubIssue.class)  // Burada Issue sınıfını belirtiyoruz
                    .collectList()
                    .block();
            System.out.println("issues : "+issues);
            if (issues == null || issues.isEmpty()) {
                hasNextPage = false;
            } else {
                allIssues.addAll(issues);
                page++;
            }

        }

        return allIssues;
    }





    //        while (hasNextPage) {
//            List<String> issues = webClient.get()
//                    .uri("/repos/{owner}/{repo}/issues?page={page}&per_page=100", owner, repo, page)
//                    .retrieve()
//                    .bodyToFlux(String.class)
//                    .collectList()
//                    .block();
//
//            if (issues != null && !issues.isEmpty()) {
//                allIssues.addAll(issues);
//                page++;
//            } else {
//                hasNextPage = false;
//            }
//        }
//    public List<String> getAllRepoIssues(String owner, String repo) {
//        WebClient webClient = WebClient.builder()
//                .baseUrl(githubApiBaseUrl)
//                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + authToken)
//                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
//                .defaultHeader("X-GitHub-Api-Version", "2022-11-28")
//                .build();
//
//        List<String> allIssues = new ArrayList<>();
//        int page = 1;
//        boolean hasNextPage = true;
//
//        while (hasNextPage) {
//            List<String> issues = webClient.get()
//                    .uri("/repos/{owner}/{repo}/issues?page={page}&per_page=100", owner, repo, page)
//                    .retrieve()
//                    .bodyToFlux(String.class)
//                    .collectList()
//                    .block();
//
//            if (issues != null && !issues.isEmpty()) {
//                allIssues.addAll(issues);
//                page++;
//            } else {
//                hasNextPage = false;
//            }
//        }
//
//        return allIssues;
//    }
}

//
//@Service
//public class GithubIssueService {
//
//    private static final Logger log = LoggerFactory.getLogger(GithubIssueService.class);
//
//    @Value("${github.api.base-url}")
//    private String githubApiBaseUrl;
//
//    @Value("${github.auth.token}")
//    private String authToken;
//
//    private final WebClient webClient;
//
//    public GithubIssueService() {
//        this.webClient = WebClient.builder()
//                .baseUrl(githubApiBaseUrl)
//                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + authToken)
//                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
//                .defaultHeader("X-GitHub-Api-Version", "2022-11-28")
//                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(1024 * 1024*250)) // Set maxInMemorySize to 1MB
//                .build();
//    }
//
//    public List<String> getAllRepoIssues(String owner, String repo) {
//        List<String> allIssues = new ArrayList<>();
//        AtomicInteger page = new AtomicInteger(1);
//        boolean hasNextPage = true;
//
//        while (hasNextPage) {
//            List<String> issues = webClient.get()
//                    .uri("/repos/{owner}/{repo}/issues?page={page}&per_page=100", owner, repo, page.get())
//                    .retrieve()
//                    .onStatus(HttpStatus::is4xxClientError, clientResponse ->
//                            handleRateLimitExceeded(clientResponse, page.get()))
//                    .onStatus(HttpStatus::is5xxServerError, this::handleServerError)
//                    .bodyToFlux(String.class)
//                    .collectList()
//                    .block();
//
//            if (issues != null && !issues.isEmpty()) {
//                allIssues.addAll(issues);
//                page.incrementAndGet();
//            } else {
//                hasNextPage = false;
//            }
//        }
//
//        return allIssues;
//    }
//
//    private Mono<? extends Throwable> handleRateLimitExceeded(ClientResponse clientResponse, int currentPage) {
//        HttpHeaders headers = clientResponse.headers().asHttpHeaders();
//        log.warn("Rate Limit Exceeded - Limit: {}, Remaining: {}, Retrying after backoff...",
//                headers.getFirst("X-RateLimit-Limit"),
//                headers.getFirst("X-RateLimit-Remaining"));
//
//        // Implement retry logic with backoff using currentPage
//        // You may also want to log and handle this situation accordingly
//        return Mono.empty();
//    }
//
//    private Mono<? extends Throwable> handleServerError(ClientResponse clientResponse) {
//        log.error("Server error: {}", clientResponse.statusCode());
//        // Implement error handling for server errors
//        return Mono.empty();
//    }
//}




//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Service;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Flux;
//
//@Service
//public class GithubIssueService {
//
//    private static final Logger log = LoggerFactory.getLogger(GithubIssueService.class);
//    private final WebClient webClient;
//
//    @Autowired
//    public GithubIssueService(WebClient.Builder webClientBuilder,
//                              @Value("${github.api.base-url}") String githubApiBaseUrl,
//                              @Value("${github.auth.token}") String authToken) {
//        this.webClient = webClientBuilder.baseUrl(githubApiBaseUrl)
//                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + authToken)
//                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
//                .defaultHeader("X-GitHub-Api-Version", "2022-11-28")
//                .exchangeStrategies(ExchangeStrategies.builder()
//                        .codecs(configurer ->
//                                configurer.defaultCodecs().maxInMemorySize(250 * 1024 * 1024)) // 20MB
//                        .build())
//                .clientConnector(new ReactorClientHttpConnector())
//                .build();
//    }
//
////    public Flux<String> getAllRepoIssues(String owner, String repo) {
////        int pageSize = 100;
////
////        return Flux.range(1, Integer.MAX_VALUE)
////                .flatMap(page -> webClient.get()
////                        .uri("/repos/{owner}/{repo}/issues?per_page={pageSize}&page={page}", owner, repo, pageSize, page)
////                        .retrieve()
////                        .bodyToFlux(String.class)
////                        .doOnNext(response -> log.info("Response body: {}", response))
////                        .doOnError(error -> log.error("Error: {}", error.getMessage()))
////                )
////                .takeWhile(response -> response != null && !response.isEmpty());
////    }
//
//
//    public Flux<String> getAllRepoIssues(String owner, String repo) {
//        int pageSize = 100;
//
//        return Flux.range(1, Integer.MAX_VALUE)
//                .flatMap(page -> webClient.get()
//                        .uri("/repos/{owner}/{repo}/issues?per_page={pageSize}&page={page}", owner, repo, pageSize, page)
//                        .retrieve()
//                        .bodyToFlux(String.class)
//                        .doOnNext(response -> log.info("Response body: {}", response))
//                        .doOnError(error -> log.error("Error: {}", error.getMessage()))
//                )
//                .takeWhile(response -> response != null && !response.isEmpty());
//    }
//
//}


















































//package com.githubIssueTrack.githubIssueTrack.githubIssue;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Service;
//import org.springframework.web.reactive.function.client.WebClient;
//import org.springframework.web.reactive.function.BodyInserters;
//import reactor.core.publisher.Flux;
//
//@Service
//public class GithubIssueService {
//
//    @Value("${github.api.base-url}")
//    private String githubApiBaseUrl;
//
//    @Value("${github.auth.token}")
//    private String authToken;
//
//    public Flux<String> getAllRepoIssues(String owner, String repo) {
//        WebClient webClient = WebClient.builder()
//                .baseUrl(githubApiBaseUrl)
//                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + authToken)
//                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
//                .defaultHeader("X-GitHub-Api-Version", "2022-11-28")
//                .build();
//
//        // İlk sayfayı al
//        Flux<String> firstPage = getRepoIssuesPage(webClient, owner, repo, 1);
//
//        // Diğer sayfaları al
//        return firstPage.flatMapMany(response -> {
//            // İlk sayfayı işle
//            int totalPages = Integer.parseInt(response.headers().header("Link").get(0).split(";")[1].trim().split("=")[1].replace("\"", ""));
//            Flux<String> otherPages = Flux.empty();
//            for (int page = 2; page <= totalPages; page++) {
//                otherPages = otherPages.concatWith(getRepoIssuesPage(webClient, owner, repo, page));
//            }
//            return otherPages;
//        });
//    }
//
//    private Flux<String> getRepoIssuesPage(WebClient webClient, String owner, String repo, int page) {
//        return webClient.get()
//                .uri("/repos/{owner}/{repo}/issues?page={page}", owner, repo, page)
//                .retrieve()
//                .bodyToFlux(String.class);
//    }
//}

//package com.githubIssueTrack.githubIssueTrack.githubIssue;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Service;
//import org.springframework.web.reactive.function.client.WebClient;
//import org.springframework.web.reactive.function.BodyInserters;
//import reactor.core.publisher.Flux;
//
//@Service
//public class GithubIssueService {
//
//    @Value("${github.api.base-url}")
//    private String githubApiBaseUrl;
//
//    @Value("${github.auth.token}")
//    private String authToken;
//
//    public Flux<String> getAllRepoIssues(String owner, String repo) {
//        WebClient webClient = WebClient.builder()
//                .baseUrl(githubApiBaseUrl)
//                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + authToken)
//                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
//                .defaultHeader("X-GitHub-Api-Version", "2022-11-28")
//                .build();
//
//        // İlk sayfayı al
//        Flux<String> firstPage = getRepoIssuesPage(webClient, owner, repo, 1);
//
//
//        // Diğer sayfaları al
//        return firstPage.flatMap(response -> {
//            // İlk sayfayı işle
//            int totalPages = Integer.parseInt(response.headers().header("Link").get(0).split(";")[1].trim().split("=")[1].replace("\"", ""));
//            Flux<String> otherPages = Flux.empty();
//            for (int page = 2; page <= totalPages; page++) {
//                otherPages = otherPages.concatWith(getRepoIssuesPage(webClient, owner, repo, page));
//            }
//            return otherPages;
//        });
//    }
//
//    private Flux<String> getRepoIssuesPage(WebClient webClient, String owner, String repo, int page) {
//        return webClient.get()
//                .uri("/repos/{owner}/{repo}/issues?page={page}", owner, repo, page)
//                .retrieve()
//                .bodyToFlux(String.class);
//    }
//}


//
//package com.githubIssueTrack.githubIssueTrack.githubIssue;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Service;
//import org.springframework.web.reactive.function.client.ClientResponse;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Flux;
//
//
//@Service
//public class GithubIssueService {
//
//    @Value("${github.api.base-url}")
//    private String githubApiBaseUrl;
//
//    @Value("${github.auth.token}")
//    private String authToken;
//
//    private final WebClient webClient;
//
//    public GithubIssueService(WebClient.Builder webClientBuilder) {
//        this.webClient = webClientBuilder
//                .baseUrl(githubApiBaseUrl)
//                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + authToken)
//                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
//                .defaultHeader("X-GitHub-Api-Version", "2022-11-28")
//                .build();
//    }
//
//    public Flux<String> getAllRepoIssues(String owner, String repo) {
//        return getRepoIssuesPage(owner, repo, 1)
//                .flatMapMany(firstPageResponse -> {
//                    int totalPages = extractTotalPages(firstPageResponse);
//                    Flux<String> otherPages = Flux.empty();
//
//                    for (int page = 2; page <= totalPages; page++) {
//                        otherPages = otherPages.concatWith(getRepoIssuesPage(owner, repo, page));
//                    }
//
//                    return otherPages;
//                })
//                .concatWithValues(""); // Add an empty value to make it Flux<String>
//    }
//
//
//    private Flux<String> getRepoIssuesPage(String owner, String repo, int page) {
//        return webClient.get()
//                .uri("/repos/{owner}/{repo}/issues?page={page}", owner, repo, page)
//                .retrieve()
//                .toEntity(String.class)
//                .flatMapMany(entity -> Flux.just(entity.getBody()));
//    }
//
//    private int extractTotalPages(ClientResponse response) {
//        String linkHeader = response.headers().header("Link").get(0);
//        return Integer.parseInt(linkHeader.split(";")[1].trim().split("=")[1].replace("\"", ""));
//    }
//}



