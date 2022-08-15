/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

/**
 *
 * @author Admin
 */
public class OrderBy{
    public String OrderByProduct(String choose){
    String order = "";
    switch(choose){
        case "a1":
            order = " Order by Update_At DESC";
            break;
        case "a2": 
            order = " Order by Update_At ASC";
            break;
        case "b1":
            order = " Order by (Price * (100 - Discount)/100) DESC";
            break;
        case "b2": 
            order = " Order by (Price * (100 - Discount)/100) ASC";
            break;
        default:
            order = " Order by [view] DESC";
    }
    return order;
    }
    
    public String OrderByProductMarketing(String choose){
    String order = "";
    switch(choose){
        case "1":
            order = " order by pr.Author DESC";
            break;
        case "2":
            order = " order by pr.CategoryID DESC";
            break;
        case "3": 
            order = " order by pr.Title DESC";
            break;
        case "4":
            order = " Order by (Price * (100 - Discount)/100) DESC";
            break;
        case "5": 
            order = " order by pr.Discount DESC";
            break;
        default:
            order = " order by pr.ProductID DESC";
    }
    return order;
    }
}
