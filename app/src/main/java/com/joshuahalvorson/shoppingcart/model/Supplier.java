package com.joshuahalvorson.shoppingcart.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Supplier implements Serializable {
    @SerializedName("supplierId")
    @Expose
    private Integer supplierId;
    @SerializedName("supplierName")
    @Expose
    private String supplierName;
    @SerializedName("supplier_products")
    @Expose
    private List<Product> supplierProducts = null;

    public Supplier(String supplierName, List<Product> supplierProducts) {
        this.supplierName = supplierName;
        this.supplierProducts = supplierProducts;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public List<Product> getSupplierProducts() {
        return supplierProducts;
    }

    public void setSupplierProducts(List<Product> supplierProducts) {
        this.supplierProducts = supplierProducts;
    }
}
