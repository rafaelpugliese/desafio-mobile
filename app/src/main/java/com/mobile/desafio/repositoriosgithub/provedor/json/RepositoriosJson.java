package com.mobile.desafio.repositoriosgithub.provedor.json;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RepositoriosJson {

    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public class Item {

        private Integer id;
        private String name;
        private String description;
        private Integer forks;

        @SerializedName("stargazers_count")
        private Integer stargazersCount;

        @SerializedName("full_name")
        private String fullName;
        private Owner owner;

        public Owner getOwner() {
            return owner;
        }

        public String getDescription() {
            return description;
        }

        public Integer getForks() {
            return forks;
        }

        public String getFullName() {
            return fullName;
        }

        public String getName() {
            return name;
        }

        public Integer getStargazersCount() {
            return stargazersCount;
        }

        public Integer getId() {
            return id;
        }

        public class Owner {

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
    }
}