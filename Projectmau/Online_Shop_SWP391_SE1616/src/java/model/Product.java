/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class Product {
    private int productID; /*Product ID*/
    private User author; /*The person who posted the product*/
    private String title; /*Product Name*/
    private String summary; /*Product ID*/
    private Setting category; /*Product description*/
    private double price; /*Product Price*/
    private double discount; /*Discount "Calculated as a Percent"*/
    private int quantity; /*Discount "Calculated as a Percent"*/
    private Date createAt; /*Product creation date*/
    private Date updateAt; /*Product upgrade date*/
    private Setting status; /**/
    private String thumbnail; /**/
    private ArrayList<Img> listImg /**/;
    private int view;
    private double rate;

    public Product() {
    }

    public Product(int productID, User author, String title, String summary, Setting category, double price, double discount, int quantity, Date createAt, Date updateAt, Setting status, String thumbnail, ArrayList<Img> listImg, int view, double rate) {
        this.productID = productID;
        this.author = author;
        this.title = title;
        this.summary = summary;
        this.category = category;
        this.price = price;
        this.discount = discount;
        this.quantity = quantity;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.status = status;
        this.thumbnail = thumbnail;
        this.listImg = listImg;
        this.view = view;
        this.rate = rate;
    }



    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Setting getCategory() {
        return category;
    }

    public void setCategory(Setting category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public Setting getStatus() {
        return status;
    }

    public void setStatus(Setting status) {
        this.status = status;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public ArrayList<Img> getListImg() {
        return listImg;
    }

    public void setListImg(ArrayList<Img> listImg) {
        this.listImg = listImg;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }   

    @Override
    public String toString() {
        return "Product{" + "productID=" + productID + ", author=" + author + ", title=" + title + ", summary=" + summary + ", category=" + category + ", price=" + price + ", discount=" + discount + ", quantity=" + quantity + ", createAt=" + createAt + ", updateAt=" + updateAt + ", status=" + status + ", thumbnail=" + thumbnail + ", listImg=" + listImg + ", view=" + view + ", rate=" + rate + '}';
    }
    
    
}
