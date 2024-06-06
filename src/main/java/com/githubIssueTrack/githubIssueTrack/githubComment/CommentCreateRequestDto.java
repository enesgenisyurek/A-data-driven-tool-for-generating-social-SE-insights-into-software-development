package com.githubIssueTrack.githubIssueTrack.githubComment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.githubIssueTrack.githubIssueTrack.githubReaction.GithubReaction;
import com.githubIssueTrack.githubIssueTrack.githubUser.GithubUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentCreateRequestDto {

    private Long id;
    private String url;
    private Long user;
    private Date created_at;
    private Date updated_at;
    private String authorAssociation;
    private String body;
    private Long reactions;

}
