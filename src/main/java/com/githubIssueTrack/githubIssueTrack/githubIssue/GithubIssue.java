package com.githubIssueTrack.githubIssueTrack.githubIssue;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.githubIssueTrack.githubIssueTrack.GithubAssignee.GithubAssignee;
import com.githubIssueTrack.githubIssueTrack.GithubMilestone.GithubMilestone;
import com.githubIssueTrack.githubIssueTrack.githubComment.GithubComment;
import com.githubIssueTrack.githubIssueTrack.githubLabel.GithubLabel;
import com.githubIssueTrack.githubIssueTrack.githubUser.GithubUser;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
public class GithubIssue {



    private Long id;
    private Long number;
    //private String url;
    //@Column(columnDefinition="CLOB")
    private String title;
    //private String comments_url;
    private String state;
    private boolean locked;
   // private List<GithubAssignee> assignee;
    //@OneToMany(fetch = FetchType.LAZY)

    //private List<GithubAssignee> assignees;
    //@ManyToOne(fetch = FetchType.LAZY)

    //private GithubMilestone milestone;
    private Long comments;
    private Date created_at;
    private Date updated_at;
    private Date closed_at;
    private String author_association;
    private String active_lock_reason;
    private boolean draft;
    //@ManyToOne(fetch = FetchType.LAZY)

    private GithubUser user;
    private List<GithubLabel> labels;
    //@OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
    private List<GithubComment> commentList;

    //@ManyToOne(fetch = FetchType.LAZY)









}
