package com.dormitory.retrofit_test.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PostList {

    @SerializedName("posts")
    @Expose
    private ArrayList<Post> posts = new ArrayList<>();


    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }
}
