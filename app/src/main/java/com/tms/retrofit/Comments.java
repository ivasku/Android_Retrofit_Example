package com.tms.retrofit;

import com.google.gson.annotations.SerializedName;

public class Comments {

    private int postId;
    private int id;
    private String name;

    @SerializedName("email")
    private String mail;

    private String body;

    public int getPostId() {
        return postId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMail() {
        return mail;
    }

    public String getBody() {
        return body;
    }
}
