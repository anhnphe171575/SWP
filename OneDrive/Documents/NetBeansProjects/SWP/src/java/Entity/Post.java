/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.util.Date;

/**
 *
 * @author admin
 */
public class Post {
    private int postID;
    private String thumbnail;
    private String title;
    private CategoryPost cp;
    private int featured;
    private int status;
    private String brief_information;
    private String description;
    private int flag;
    private User user;
    private Date date_create_by;

    public Post(int postID, String thumbnail, String title, CategoryPost cp, int featured, int status, String brief_information, String description, int flag, User user, Date date_create_by) {
        this.postID = postID;
        this.thumbnail = thumbnail;
        this.title = title;
        this.cp = cp;
        this.featured = featured;
        this.status = status;
        this.brief_information = brief_information;
        this.description = description;
        this.flag = flag;
        this.user = user;
        this.date_create_by = date_create_by;
    }

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public CategoryPost getCp() {
        return cp;
    }

    public void setCp(CategoryPost cp) {
        this.cp = cp;
    }

    public int getFeatured() {
        return featured;
    }

    public void setFeatured(int featured) {
        this.featured = featured;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getBrief_information() {
        return brief_information;
    }

    public void setBrief_information(String brief_information) {
        this.brief_information = brief_information;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate_create_by() {
        return date_create_by;
    }

    public void setDate_create_by(Date date_create_by) {
        this.date_create_by = date_create_by;
    }

    @Override
    public String toString() {
        return "Post{" + "postID=" + postID + ", thumbnail=" + thumbnail + ", title=" + title + ", cp=" + cp + ", featured=" + featured + ", status=" + status + ", brief_information=" + brief_information + ", description=" + description + ", flag=" + flag + ", user=" + user + ", date_create_by=" + date_create_by + '}';
    }

    

    
    
    
}
