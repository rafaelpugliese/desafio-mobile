package com.mobile.desafio.repositoriosgithub.provedor.json;

import com.google.gson.annotations.SerializedName;

public class PullJson {

    private String title;
    private String body;
    private User user;
    @SerializedName("merged_at")
    private String merged;
    private String state;
    private Integer id;

    @SerializedName("created_at")
    private String dataCriacao;

    @SerializedName("html_url")
    private String html;

    public String getBody() {
        return body;
    }

    public String getTitle() {
        return title;
    }

    public User getUser() {
        return user;
    }

    public String getHtml() {
        return html;
    }

    public Integer getId() {
        return id;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public class User {

        private Integer id;

        private String login;

        @SerializedName("avatar_url")
        private String avatarUrl;

        public String getAvatarUrl() {
            return avatarUrl;
        }

        public String getLogin() {
            return login;
        }

        public Integer getId() {
            return id;
        }
    }

    public String getMerged() {
        return merged;
    }

    public String getState() {
        return state;
    }

    public boolean isOpen() {
        return "open".equals(state);
    }

    public boolean isMerged() {
        return merged != null;
    }

    public class Milestone {

        @SerializedName("open_issues")
        private String openIssues;

        @SerializedName("closed_issues")
        private String closedIssues;

        public String getClosedIssues() {
            return closedIssues;
        }

        public String getOpenIssues() {
            return openIssues;
        }
    }

}