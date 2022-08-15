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
public class CartDetail {

    private int cartDetailID;
    private int cartID;
    private Product product;
    private int quantity;
    private int totalPriceOfProduct;

    public CartDetail() {
    }

    public CartDetail(int cartDetailID, int cartID, Product product, int quantity, int totalPriceOfProduct) {
        this.cartDetailID = cartDetailID;
        this.cartID = cartID;
        this.product = product;
        this.quantity = quantity;
        this.totalPriceOfProduct = totalPriceOfProduct;
    }

    public int getCartDetailID() {
        return cartDetailID;
    }

    public void setCartDetailID(int cartDetailID) {
        this.cartDetailID = cartDetailID;
    }

    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotalPriceOfProduct() {
        return totalPriceOfProduct;
    }

    public void setTotalPriceOfProduct(int totalPriceOfProduct) {
        this.totalPriceOfProduct = totalPriceOfProduct;
    }

}
