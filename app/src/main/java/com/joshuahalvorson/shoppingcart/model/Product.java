package com.joshuahalvorson.shoppingcart.model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product implements Serializable {

    @SerializedName("productId")
    @Expose
    private Integer productId;
    @SerializedName("productName")
    @Expose
    private String productName;
    @SerializedName("productDescription")
    @Expose
    private String productDescription;
    @SerializedName("productCost")
    @Expose
    private double productCost;
    @SerializedName("productOnHand")
    @Expose
    private Integer productOnHand;
    @SerializedName("suppliers")
    @Expose
    private List<Object> suppliers = null;
    @SerializedName("orderId")
    @Expose
    private long orderId;
    @SerializedName("productActive")
    @Expose
    private boolean productActive;

    public Product(String productName, String productDescription, double productCost, Integer productOnHand,
                   long orderid, boolean productActive) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productCost = productCost;
        this.productOnHand = productOnHand;
        this.orderId = orderid;
        this.productActive = productActive;
    }

    public boolean isProductActive() {
        return productActive;
    }

    public void setProductActive(boolean productActive) {
        this.productActive = productActive;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public double getProductCost() {
        return productCost;
    }

    public void setProductCost(double productCost) {
        this.productCost = productCost;
    }

    public Integer getProductOnHand() {
        return productOnHand;
    }

    public void setProductOnHand(Integer productOnHand) {
        this.productOnHand = productOnHand;
    }

    public List<Object> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<Object> suppliers) {
        this.suppliers = suppliers;
    }

}
