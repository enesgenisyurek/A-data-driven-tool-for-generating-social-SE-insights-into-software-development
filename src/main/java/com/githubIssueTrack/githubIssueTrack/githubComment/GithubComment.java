package com.githubIssueTrack.githubIssueTrack.githubComment;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.githubIssueTrack.githubIssueTrack.githubReaction.GithubReaction;
import com.githubIssueTrack.githubIssueTrack.githubUser.GithubUser;
import lombok.*;
import org.hibernate.engine.profile.Fetch;

import javax.persistence.*;
import java.util.Date;

@Data
@Getter
@Setter
public class GithubComment {



    private Long id;
    //private String url; // burada url içerisinde repo adı ve comment id çekilebilir

   // @ManyToOne(fetch = FetchType.LAZY)

    private GithubUser user;

    private Date created_at;
    private Date updated_at;
    private String authorAssociation;
    private String body;
   // @ManyToOne(fetch = FetchType.LAZY)

    private GithubReaction reactions;


}
