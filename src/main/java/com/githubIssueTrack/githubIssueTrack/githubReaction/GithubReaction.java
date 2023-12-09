package com.githubIssueTrack.githubIssueTrack.githubReaction;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GithubReaction {

    private String like;
    private String dislike;
    private String laugh;
    private String hooray;
    private String confused;
    private String heart;
    private String rocket;
    private String eyes;

    @Override
    public String toString() {
        return "GithubReaction{" +
                "like='" + like + '\'' +
                ", dislike='" + dislike + '\'' +
                ", laugh='" + laugh + '\'' +
                ", hooray='" + hooray + '\'' +
                ", confused='" + confused + '\'' +
                ", heart='" + heart + '\'' +
                ", rocket='" + rocket + '\'' +
                ", eyes='" + eyes + '\'' +
                '}';
    }
}
