package com.githubIssueTrack.githubIssueTrack.githubReaction;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Getter
@Setter
public class GithubReaction {


    private long like;
    private long dislike;
    private String laugh;
    private String hooray;
    private String confused;
    private String heart;
    private String rocket;
    private String eyes;


}
