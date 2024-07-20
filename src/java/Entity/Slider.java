/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.util.Date;

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
    private Staff staff;
    private int page_order;
    private Date slider_date_createby;

    
     public Slider(){
         
     }

    public Slider(int sliderID, String title, String image, String link, int status, String notes, Staff staff, int page_order, Date slider_date_createby) {
        this.sliderID = sliderID;
        this.title = title;
        this.image = image;
        this.link = link;
        this.status = status;
        this.notes = notes;
        this.staff = staff;
        this.page_order = page_order;
        this.slider_date_createby = slider_date_createby;
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

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public int getPage_order() {
        return page_order;
    }

    public void setPage_order(int page_order) {
        this.page_order = page_order;
    }

    public Date getSlider_date_createby() {
        return slider_date_createby;
    }

    public void setSlider_date_createby(Date slider_date_createby) {
        this.slider_date_createby = slider_date_createby;
    }
  

}
