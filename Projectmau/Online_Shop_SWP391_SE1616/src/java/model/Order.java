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
public class Order {
    private int orderID;
    private User cusID;
    private Date orderDate;
    private double totalBill;
    private int totalProduct;
    private String orderAddress;
    private Setting status;
    private String note;
    private Receiver receive;
    private ArrayList<OrderDetail> listProduct;

    public Order() {
    }

    public Order(int orderID, User cusID, Date orderDate, double totalBill, int totalProduct, String orderAddress, Setting status, String note, Receiver receive, ArrayList<OrderDetail> listProduct) {
        this.orderID = orderID;
        this.cusID = cusID;
        this.orderDate = orderDate;
        this.totalBill = totalBill;
        this.totalProduct = totalProduct;
        this.orderAddress = orderAddress;
        this.status = status;
        this.note = note;
        this.receive = receive;
        this.listProduct = listProduct;
    }



    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public User getCusID() {
        return cusID;
    }

    public void setCusID(User cusID) {
        this.cusID = cusID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(double totalBill) {
        this.totalBill = totalBill;
    }

    public int getTotalProduct() {
        return totalProduct;
    }

    public void setTotalProduct(int totalProduct) {
        this.totalProduct = totalProduct;
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress;
    }

    public Setting getStatus() {
        return status;
    }

    public void setStatus(Setting status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Receiver getReceive() {
        return receive;
    }

    public void setReceive(Receiver receive) {
        this.receive = receive;
    }

    public ArrayList<OrderDetail> getListProduct() {
        return listProduct;
    }

    public void setListProduct(ArrayList<OrderDetail> listProduct) {
        this.listProduct = listProduct;
    }

    @Override
    public String toString() {
        return "Order{" + "orderID=" + orderID + ", cusID=" + cusID + ", orderDate=" + orderDate + ", totalBill=" + totalBill + ", totalProduct=" + totalProduct + ", orderAddress=" + orderAddress + ", status=" + status + ", note=" + note + ", receive=" + receive + ", listProduct=" + listProduct + '}';
    }

    
    
    
}