package com.githubIssueTrack.githubIssueTrack.githubIssue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;


//@RestController
//@RequestMapping("/github")
//public class GithubIssueController {
//
//    @Autowired
//    private GithubIssueService githubIssueService;
//
//
//
//    @GetMapping("/repos/{owner}/{repo}/issues")
//    public String getRepoIssues(@PathVariable String owner, @PathVariable String repo) {
//        return githubIssueService.getRepoIssues(owner, repo);
//        //return githubIssueService.getRepo();
//    }
//
//}



@RestController
@RequestMapping("/github")
public class GithubIssueController {

    @Autowired
    private GithubIssueService githubIssueService;

    @GetMapping("/repos/{owner}/{repo}/issues")
    public List<GithubIssue> getAllRepoIssues(@PathVariable String owner, @PathVariable String repo) {
        //return githubIssueService.getRepoIssues(owner, repo);
        return githubIssueService.getAllRepoIssues123(owner,repo);
    }

//    @GetMapping("/repos/{owner}/{repo}/issues")
//    public Flux<String> getRepoIssues(@PathVariable String owner, @PathVariable String repo) {
//        return githubIssueService.getAllRepoIssues(owner, repo);
//        // githubIssueService.getIssues(owner,repo);
//        //return githubIssueService.getRepo();
//
//
//    }
//
//    @GetMapping("/repos/{owner}/{repo}/issues")
//    public Issue[] getIssues(@PathVariable String owner, @PathVariable String repo) {
//
//        // Create a GitHubClient object
//        GithubClient gitHubClient = new GithubClient();
//
//        // Call the listIssues method with the owner and repo parameters
//        Issue[] issues = gitHubClient.listIssues(owner, repo);
//
//        // Return the issues array
//        return issues;
//
//
//    }


}

