package com.githubIssueTrack.githubIssueTrack.githubComment;

import com.githubIssueTrack.githubIssueTrack.githubReaction.GithubReaction;
import com.githubIssueTrack.githubIssueTrack.githubUser.GithubUser;
import java.util.Date;

public class GithubComment {

    private Long id;
    private Long issueId;
    private GithubUser user;
    private Date createdAt;
    private Date updatedAt;
    private String authorAssociation;
    private String body;
    private GithubReaction reactions;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIssueId() {
        return issueId;
    }

    public void setIssueId(Long issueId) {
        this.issueId = issueId;
    }

    public GithubUser getUser() {
        return user;
    }

    public void setUser(GithubUser user) {
        this.user = user;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getAuthorAssociation() {
        return authorAssociation;
    }

    public void setAuthorAssociation(String authorAssociation) {
        this.authorAssociation = authorAssociation;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public GithubReaction getReactions() {
        return reactions;
    }

    public void setReactions(GithubReaction reactions) {
        this.reactions = reactions;
    }
}
