package com.jo.assignmentdotjo.network.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Saif M Jaradat on 1/28/2021.
 */
public class Contribute {

    @SerializedName("avatar_url")
    private String avatar;

    @SerializedName("login")
    private String name;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Contribute{" +
                "avatar='" + avatar + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
