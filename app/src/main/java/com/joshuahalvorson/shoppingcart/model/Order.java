package com.joshuahalvorson.shoppingcart.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Order {

    @SerializedName("orderId")
    @Expose
    private Integer orderId;
    @SerializedName("products")
    @Expose
    private List<Product> products = null;
    @SerializedName("orderShippingAddress")
    @Expose
    private String orderShippingAddress;
    @SerializedName("orderPaymentMethod")
    @Expose
    private String orderPaymentMethod;
    @SerializedName("orderShipped")
    @Expose
    private Boolean orderShipped;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getOrderShippingAddress() {
        return orderShippingAddress;
    }

    public void setOrderShippingAddress(String orderShippingAddress) {
        this.orderShippingAddress = orderShippingAddress;
    }

    public String getOrderPaymentMethod() {
        return orderPaymentMethod;
    }

    public void setOrderPaymentMethod(String orderPaymentMethod) {
        this.orderPaymentMethod = orderPaymentMethod;
    }

    public Boolean getOrderShipped() {
        return orderShipped;
    }

    public void setOrderShipped(Boolean orderShipped) {
        this.orderShipped = orderShipped;
    }
}
