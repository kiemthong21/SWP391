/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Admin
 */
public class Img {
    private int imgId; /*Img ID*/
    private String type;/**/
    private int allID; 
    private String link;

    public Img() {
    }

    public Img(int imgId, String type, int allID, String link) {
        this.imgId = imgId;
        this.type = type;
        this.allID = allID;
        this.link = link;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAllID() {
        return allID;
    }

    public void setAllID(int allID) {
        this.allID = allID;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    
}
