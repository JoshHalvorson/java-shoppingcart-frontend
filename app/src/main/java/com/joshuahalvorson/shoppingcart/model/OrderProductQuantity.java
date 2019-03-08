package com.joshuahalvorson.shoppingcart.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderProductQuantity {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("orderId")
    @Expose
    private Integer orderId;
    @SerializedName("product")
    @Expose
    private Product product;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
