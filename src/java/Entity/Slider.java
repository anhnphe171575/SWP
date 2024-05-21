/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author phuan
 */
public class Slider {
    private int sliderID;
    private String title;
    private String image;
    private String link;
    private int status;
    private String notes;
    private User user;

    public Slider(int sliderID, String title, String image, String link, int status, String notes, User user) {
        this.sliderID = sliderID;
        this.title = title;
        this.image = image;
        this.link = link;
        this.status = status;
        this.notes = notes;
        this.user = user;
    }

    public int getSliderID() {
        return sliderID;
    }

    public void setSliderID(int sliderID) {
        this.sliderID = sliderID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Slider{" + "sliderID=" + sliderID + ", title=" + title + ", image=" + image + ", link=" + link + ", status=" + status + ", notes=" + notes + ", user=" + user + '}';
    }
    
}
