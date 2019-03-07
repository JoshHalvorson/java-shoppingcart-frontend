package com.joshuahalvorson.shoppingcart.network;

import com.google.gson.JsonObject;
import com.joshuahalvorson.shoppingcart.model.Cart;
import com.joshuahalvorson.shoppingcart.model.Order;
import com.joshuahalvorson.shoppingcart.model.Product;
import com.joshuahalvorson.shoppingcart.model.Shopper;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ShoppingCartClient {
    String BASE_URL = "http://10.0.2.2:8080/";

    @GET("products")
    Call<List<Product>> getAllProducts();

    @GET("cart")
    Call<List<Cart>> getCartProducts();

    @POST("shopkeeper/product")
    Call<Product> addProduct(@Body Product product);

    @POST("cart/add/{productid}")
    Call<Product> addProductToCart(@Header("quantity") int quantity,
                                   @Path("productid") long productid);

    @POST("shopper")
    Call<Shopper> addShopper(@Body Shopper shopper);

    @POST("order")
    Call<Order> addOrder(@Body Order order);

    @PUT("shopkeeper/product/{productid}")
    Call<Product> updateProduct(@Body Product product, @Path("productid") long productid);

    @DELETE("cart/remove/{productid}")
    Call<Product> removeProductFromCart(@Path("productid") long productid);

    @DELETE("shopkeeper/product/{productid}")
    Call<Product> removeProduct(@Path("productid") long productid);
}
