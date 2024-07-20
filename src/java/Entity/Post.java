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
    private Staff staff;
    private Date date_create_by;
    
     public Post(){
         
     }
    public Post(int postID, String thumbnail, String title, CategoryPost cp, int featured, int status, String brief_information, String description, Staff staff, Date date_create_by) {
        this.postID = postID;
        this.thumbnail = thumbnail;
        this.title = title;
        this.cp = cp;
        this.featured = featured;
        this.status = status;
        this.brief_information = brief_information;
        this.description = description;
        this.staff = staff;
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

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Date getDate_create_by() {
        return date_create_by;
    }

    public void setDate_create_by(Date date_create_by) {
        this.date_create_by = date_create_by;
    }

  
}
