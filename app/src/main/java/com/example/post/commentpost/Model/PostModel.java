package com.example.post.commentpost.Model;

public class PostModel {
    private String title;
    private String body;
    private String postUserId;
    private String postId;
    public PostModel(String title, String body, String postUserId, String postId){
        this.title = title;
        this.body = body;
        this.postUserId = postUserId;
        this.postId = postId;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPostUserId() {
        return postUserId;
    }

    public void setPostUserId(String postUserId) {
        this.postUserId = postUserId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }
}
