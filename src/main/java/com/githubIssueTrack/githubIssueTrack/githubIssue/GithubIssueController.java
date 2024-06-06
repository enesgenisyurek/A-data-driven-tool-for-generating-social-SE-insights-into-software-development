package com.githubIssueTrack.githubIssueTrack.githubIssue;

import com.githubIssueTrack.githubIssueTrack.GithubAssignee.GithubAssignee;
import com.githubIssueTrack.githubIssueTrack.githubComment.GithubComment;
import com.githubIssueTrack.githubIssueTrack.githubUser.GithubUser;
import com.opencsv.CSVWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import org.springframework.web.bind.annotation.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/github")
public class GithubIssueController {

    @Autowired
    private GithubIssueService githubIssueService;

    @GetMapping("/repos/{owner}/{repo}/issues")
    public List<GithubIssue> getAllRepoIssues(@PathVariable String owner, @PathVariable String repo) {
//        List<GithubIssue> data = githubIssueService.getAllRepoIssues(owner,repo);
//
//        try (CSVWriter writer = new CSVWriter(new FileWriter("fileName59.csv"))) {
//            // CSV başlıklarını yaz
//            String[] header = {"id", "number", "url", "title", "comments_url","state","locked","assignees__id","assignees__login","assignees__site_admin","assignees__type",
//            "commentList__id","commentList__url","commentList__created_at","commentList__updated_at","commentList__authorAssociation"};//"commentList__body"
//            writer.writeNext(header);
//
//            //id,"number","url","title","comments_url","state","locked","assignees__login","assignees__id",
//            // "assignees__type","assignees__site_admin","milestone","comments","created_at","updated_at",
//            // "closed_at","author_association","active_lock_reason","draft","user__id","user__login",
//            // "user__type","user__siteAdmin","commentList__id","commentList__url","commentList__user__id","
//            // commentList__user__login","commentList__user__type","commentList__user__siteAdmin","commentList__created_at",
//            // "commentList__updated_at","commentList__authorAssociation","commentList__body","commentList__reactions__like",
//            // "commentList__reactions__dislike","commentList__reactions__laugh","commentList__reactions__hooray",
//            // "commentList__reactions__confused","commentList__reactions__heart","commentList__reactions__rocket",
//            // "commentList__reactions__eyes"



//
//            // GithubIssue listesini CSV'ye yaz
//            for (GithubIssue issue : data) {
//                String[] line = {
//                        String.valueOf(issue.getId()),
//                        String.valueOf(issue.getNumber()),
//                        issue.getUrl(),
//                        issue.getTitle(),
//                        issue.getComments_url(),
//                        issue.getState(),
//                        String.valueOf(issue.isLocked()),
//                        issue.getAssignees().stream().map(
//                                x -> String.valueOf(x.getId())
//                        ).collect(Collectors.joining(",")),
//                        issue.getAssignees().stream().map(
//                                x -> String.valueOf(x.getLogin())
//                        ).collect(Collectors.joining(",")),
//                        issue.getAssignees().stream().map(
//                                x -> String.valueOf(x.isSite_admin())
//                        ).collect(Collectors.joining(",")),
//                        issue.getAssignees().stream().map(
//                                x -> String.valueOf(x.getType())
//                        ).collect(Collectors.joining(",")),
//
//                        issue.getCommentList().stream().map(
//                                x -> String.valueOf(x.getId())
//                        ).collect(Collectors.joining(",")),
//
//                        issue.getCommentList().stream().map(
//                                x -> String.valueOf(x.getUrl())
//                        ).collect(Collectors.joining(",")),
//
//                            // commentUserinfo baslangic
//                        issue.getCommentList().stream().map(
//                                x -> String.valueOf(x.getUser().getUserId())
//                        ).collect(Collectors.joining(",")),
//
//                        issue.getCommentList().stream().map(
//                                x -> String.valueOf(x.getUser().getLogin())
//                        ).collect(Collectors.joining(",")),
//
//                        issue.getCommentList().stream().map(
//                                x -> String.valueOf(x.getUser().getType())
//                        ).collect(Collectors.joining(",")),
//
//                        issue.getCommentList().stream().map(
//                                x -> String.valueOf(x.getUser().isSiteAdmin())
//                        ).collect(Collectors.joining(",")),
//
//                        // bitis
//                        issue.getCommentList().stream().map(
//                                x -> String.valueOf(x.getCreated_at())
//                        ).collect(Collectors.joining(",")),
//
//                        issue.getCommentList().stream().map(
//                                x -> String.valueOf(x.getUpdated_at())
//                        ).collect(Collectors.joining(",")),
//
//                        issue.getCommentList().stream().map(
//                                x -> String.valueOf(x.getAuthorAssociation())
//                        ).collect(Collectors.joining(",")),
//
////                        issue.getCommentList().stream().map(
////                                x -> String.valueOf(x.getBody()).replace("```","")
////                        ).collect(Collectors.joining("```")),
//
//                        issue.getCommentList().stream()
//                        .map(comment -> String.valueOf(comment.getBody()).replaceAll("^```|```$", ""))
//                        .collect(Collectors.joining(" ")),
//
////                issue.getCommentList().stream()
////                                .map(x -> String.valueOf(x.getBody()).replaceAll("^```|```$", ""))
////                                .collect(Collectors.joining("\n")),
//
//
//
//
//                };
//                writer.writeNext(line);
//            }
//
//            System.out.println("Veriler CSV formatına başarıyla kaydedildi.");
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.err.println("Verileri CSV formatına kaydederken bir hata oluştu.");
//        }

        return githubIssueService.getAllRepoIssues(owner,repo);
    }

}

