package com.example.riaz.rate_batao;

/**
 * Created by abdull on 5/1/17.
 */

public class Post {
    String postTitle;
    String postDiscription;
    String Comments;
    String postReference;
    String postTitleReference;

    public String getPostTitleReference() {
        return postTitleReference;
    }

    public void setPostTitleReference(String postTitleReference) {
        this.postTitleReference = postTitleReference;
    }

    public String getComments() {
        return Comments;
    }

    public void setComments(String comments) {
        Comments = comments;
    }

    public Post() {
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostDiscription() {
        return postDiscription;
    }

    public void setPostDiscription(String postDiscription) {
        this.postDiscription = postDiscription;
    }

    public String getPostReference() {
        return postReference;
    }

    public void setPostReference(String postReference) {
        this.postReference = postReference;
    }
}
