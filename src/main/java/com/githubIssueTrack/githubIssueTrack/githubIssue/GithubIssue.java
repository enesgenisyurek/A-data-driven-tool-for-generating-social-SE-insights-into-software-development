package com.githubIssueTrack.githubIssueTrack.githubIssue;

import com.githubIssueTrack.githubIssueTrack.githubUser.GithubUser;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
public class GithubIssue {
    private Long id;
    private Long number;
    private String url;
    private Long comments;
    private String title;
    private String comments_url;
    private String state;
    private boolean locked;
    private Date created_at;
    private Date updated_at;
    private Date closed_at;
    private GithubUser user;

    @Override
    public String toString() {
        return "GithubIssue{" +
                "id=" + id +
                ", number=" + number +
                ", url='" + url + '\'' +
                ", comments=" + comments +
                ", title='" + title + '\'' +
                ", comments_url='" + comments_url + '\'' +
                ", state='" + state + '\'' +
                ", locked=" + locked +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                ", closed_at=" + closed_at +
                ", user=" + user +
                '}';
    }
}
