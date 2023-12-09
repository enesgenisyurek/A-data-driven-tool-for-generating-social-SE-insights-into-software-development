package com.githubIssueTrack.githubIssueTrack.githubUser;

public class GithubUser {

    private Long id;
    private String login;
    private String type;
    private boolean siteAdmin;

    public GithubUser() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isSiteAdmin() {
        return siteAdmin;
    }

    public void setSiteAdmin(boolean siteAdmin) {
        this.siteAdmin = siteAdmin;
    }

    @Override
    public String toString() {
        return "GithubUser{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", type='" + type + '\'' +
                ", siteAdmin=" + siteAdmin +
                '}';
    }
}
