package com.joshuahalvorson.shoppingcart.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Shopper {
    @SerializedName("shopperId")
    @Expose
    private Integer shopperId;
    @SerializedName("shopperName")
    @Expose
    private String shopperName;
    @SerializedName("shopperBillingAddress")
    @Expose
    private String shopperBillingAddress;
    @SerializedName("shopperShippingAddress")
    @Expose
    private String shopperShippingAddress;
    @SerializedName("shopperPhoneNumber")
    @Expose
    private String shopperPhoneNumber;
    @SerializedName("shopperPaymentMethod")
    @Expose
    private String shopperPaymentMethod;

    public Integer getShopperId() {
        return shopperId;
    }

    public void setShopperId(Integer shopperId) {
        this.shopperId = shopperId;
    }

    public String getShopperName() {
        return shopperName;
    }

    public void setShopperName(String shopperName) {
        this.shopperName = shopperName;
    }

    public String getShopperBillingAddress() {
        return shopperBillingAddress;
    }

    public void setShopperBillingAddress(String shopperBillingAddress) {
        this.shopperBillingAddress = shopperBillingAddress;
    }

    public String getShopperShippingAddress() {
        return shopperShippingAddress;
    }

    public void setShopperShippingAddress(String shopperShippingAddress) {
        this.shopperShippingAddress = shopperShippingAddress;
    }

    public String getShopperPhoneNumber() {
        return shopperPhoneNumber;
    }

    public void setShopperPhoneNumber(String shopperPhoneNumber) {
        this.shopperPhoneNumber = shopperPhoneNumber;
    }

    public String getShopperPaymentMethod() {
        return shopperPaymentMethod;
    }

    public void setShopperPaymentMethod(String shopperPaymentMethod) {
        this.shopperPaymentMethod = shopperPaymentMethod;
    }
}
