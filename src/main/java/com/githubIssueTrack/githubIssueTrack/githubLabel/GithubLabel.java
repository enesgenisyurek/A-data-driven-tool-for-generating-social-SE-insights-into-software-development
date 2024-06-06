package com.githubIssueTrack.githubIssueTrack.githubLabel;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Getter
@Setter
public class GithubLabel {


    //private Long id;
    private String name;
    //private String color;
    private boolean isDefault;
    private String description;


}


