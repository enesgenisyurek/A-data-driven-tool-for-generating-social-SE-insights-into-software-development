package com.githubIssueTrack.githubIssueTrack.githubComment;

import com.githubIssueTrack.githubIssueTrack.githubReaction.GithubReaction;
import com.githubIssueTrack.githubIssueTrack.githubUser.GithubUser;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
public class GithubComment {

    private Long id;
    //private Long issueId;
    private GithubUser user;
    private Date created_at;
    private Date updated_at;
    private String authorAssociation;
    private String body;
    private GithubReaction reactions;

    @Override
    public String toString() {
        return "GithubComment{" +
                "id=" + id +
                //", issueId=" + issueId +
                ", user=" + user +
                ", createdAt=" + created_at +
                ", updatedAt=" + updated_at +
                ", authorAssociation='" + authorAssociation + '\'' +
                ", body='" + body + '\'' +
                ", reactions=" + reactions +
                '}';
    }
}
