package com.joshuahalvorson.shoppingcart.network;

import com.google.gson.JsonObject;
import com.joshuahalvorson.shoppingcart.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ShoppingCartClient {
    String BASE_URL = "http://10.0.2.2:8080/";

    @GET("products")
    Call<List<Product>> getAllProducts();

    @GET("cart")
    Call<List<Product>> getCartProducts();

    @POST("shopkeeper/product")
    Call<Product> addProduct(@Body Product product);
}
