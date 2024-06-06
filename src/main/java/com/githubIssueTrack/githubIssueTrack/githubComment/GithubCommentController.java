package com.githubIssueTrack.githubIssueTrack.githubComment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/github")
public class GithubCommentController {

    @Autowired
    private GithubCommentService githubCommentService;



    @GetMapping("/repos/{owner}/{repo}/issues/{issue_number}/comments")
    public List<GithubComment> getIssueComments(@PathVariable String owner, @PathVariable String repo,@PathVariable Long issue_number) {
        return githubCommentService.getIssueComments(owner,repo,issue_number);

    }

}



