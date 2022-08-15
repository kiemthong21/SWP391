/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author Admin
 */
public class Post {
    private int postId;
    private String postTitle;
    private String postContent;
    private User author;
    private Date postDate;
    private Date updateDate;
    private String thumbnail;
    private int view;
    private String summary;
    private Setting PostCategory;
    private Setting PostStatus;

    public Post() {
    }

    public Post(int postId, String postTitle, String postContent, User author, Date postDate, Date updateDate, String thumbnail, int view, String summary, Setting PostCategory, Setting PostStatus) {
        this.postId = postId;
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.author = author;
        this.postDate = postDate;
        this.updateDate = updateDate;
        this.thumbnail = thumbnail;
        this.view = view;
        this.summary = summary;
        this.PostCategory = PostCategory;
        this.PostStatus = PostStatus;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Setting getPostCategory() {
        return PostCategory;
    }

    public void setPostCategory(Setting PostCategory) {
        this.PostCategory = PostCategory;
    }

    public Setting getPostStatus() {
        return PostStatus;
    }

    public void setPostStatus(Setting PostStatus) {
        this.PostStatus = PostStatus;
    }

   
}
