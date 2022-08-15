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
public class Slider {
    private int sliderID;
    private String title;
    private String img;
    private User author;
    private String content;
    private Date startDate;
    private Setting status;

    

    public Slider() {
    }

    public Slider(int sliderID, String title, String img, User author, String content, Date startDate, Setting status) {
        this.sliderID = sliderID;
        this.title = title;
        this.img = img;
        this.author = author;
        this.content = content;
        this.startDate = startDate;
        this.status = status;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Setting getStatus() {
        return status;
    }

    public void setStatus(Setting status) {
        this.status = status;
    }
   @Override
    public String toString() {
        return "Slider{" + "sliderID=" + sliderID + ", title=" + title + ", img=" + img + ", author=" + author + ", content=" + content + ", startDate=" + startDate + ", status=" + status + '}';
    } 
}
