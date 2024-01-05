/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author admin
 */
public class OrderDetailsDTO {

    int orderID;
    int productID;
    String productName;
    String description;
    int quantity;
    int storeQuantity;
    double unitPrice;
    double totalPrice;
    String imgLocation;

    public OrderDetailsDTO() {
    }

    public OrderDetailsDTO(int orderID, int productID, String productName, String description, int quantity, int storeQuantity, double unitPrice, double totalPrice, String imgLocation) {
        this.orderID = orderID;
        this.productID = productID;
        this.productName = productName;
        this.description = description;
        this.quantity = quantity;
        this.storeQuantity = storeQuantity;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.imgLocation = imgLocation;
    }

    public OrderDetailsDTO(int orderID, int productID, String productName, String description, int quantity, double unitPrice, double totalPrice, String imgLocation) {
        this.orderID = orderID;
        this.productID = productID;
        this.productName = productName;
        this.description = description;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.imgLocation = imgLocation;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getStoreQuantity() {
        return storeQuantity;
    }

    public void setStoreQuantity(int storeQuantity) {
        this.storeQuantity = storeQuantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getImgLocation() {
        return imgLocation;
    }

    public void setImgLocation(String imgLocation) {
        this.imgLocation = imgLocation;
    }

}
