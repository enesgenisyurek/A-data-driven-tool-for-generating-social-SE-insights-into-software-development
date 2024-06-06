package com.githubIssueTrack.githubIssueTrack.githubIssue;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.githubIssueTrack.githubIssueTrack.GithubAssignee.GithubAssignee;
import com.githubIssueTrack.githubIssueTrack.GithubMilestone.GithubMilestone;
import com.githubIssueTrack.githubIssueTrack.githubComment.GithubComment;
import com.githubIssueTrack.githubIssueTrack.githubUser.GithubUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class IssueCreateRequestDto {

    private Long id;
    private Long number;
    private String url;
    private String title;
    private String comments_url;
    private String state;
    private boolean locked;
    private List<Long> assignees;
    private Long milestone;
    private Long comments;
    private Date created_at;
    private Date updated_at;
    private Date closed_at;
    private String author_association;
    private String active_lock_reason;
    private boolean draft;
    private Long user;
    private List<Long> commentList;

}
