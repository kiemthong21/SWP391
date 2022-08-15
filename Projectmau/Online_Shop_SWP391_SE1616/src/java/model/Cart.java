/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class Cart {
    private int cartId;
    private int userId;
    private float totalPrice;
    private ArrayList<CartDetail> listProduct;

    public Cart() {
    }

    public Cart(int cartId, int userId, float totalPrice, ArrayList<CartDetail> listProduct) {
        this.cartId = cartId;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.listProduct = listProduct;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public ArrayList<CartDetail> getListProduct() {
        return listProduct;
    }

    public void setListProduct(ArrayList<CartDetail> listProduct) {
        this.listProduct = listProduct;
    }

   
}
