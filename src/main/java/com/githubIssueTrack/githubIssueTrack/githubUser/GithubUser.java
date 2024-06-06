package com.githubIssueTrack.githubIssueTrack.githubUser;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Getter
@Setter

public class GithubUser {


    private Long id;
    private String login;
    private String type;
    private boolean siteAdmin;

}
