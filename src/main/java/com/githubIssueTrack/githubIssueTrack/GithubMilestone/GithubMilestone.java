package com.githubIssueTrack.githubIssueTrack.GithubMilestone;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@Setter
@Getter
@Entity
public class GithubMilestone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long milestoneId;

    private Long id;
    private Long number;
    private String title;
    private String description;
    private Long openIssues;
    private Long closedIssues;
    private String state;
    private Date created_at;
    private Date updated_at;
    private Date closed_at;
    private String due_on;

    @Override
    public String toString() {
        return "GithubMilestone{" +
                "id=" + id +
                ", number=" + number +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", openIssues=" + openIssues +
                ", closedIssues=" + closedIssues +
                ", state='" + state + '\'' +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                ", closed_at=" + closed_at +
                ", due_on='" + due_on + '\'' +
                '}';
    }
}
