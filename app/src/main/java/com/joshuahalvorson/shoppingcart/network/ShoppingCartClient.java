package com.joshuahalvorson.shoppingcart.network;

import com.google.gson.JsonObject;
import com.joshuahalvorson.shoppingcart.model.Cart;
import com.joshuahalvorson.shoppingcart.model.Order;
import com.joshuahalvorson.shoppingcart.model.Product;
import com.joshuahalvorson.shoppingcart.model.Shopper;
import com.joshuahalvorson.shoppingcart.model.Supplier;

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

    @GET("shopkeeper/orders")
    Call<List<Order>> getAllOrders();

    @GET("shopkeeper/suppliers")
    Call<List<Supplier>> getAllSuppliers();

    @GET("admin/shoppers")
    Call<List<Shopper>> getAllShoppers();

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

    @PUT("shopkeeper/supplier/{supplierid}")
    Call<Supplier> updateSupplier(@Body Supplier supplier, @Path("supplierid") long supplierid);

    @DELETE("cart/remove/{productid}")
    Call<Product> removeProductFromCart(@Path("productid") long productid);

    @DELETE("shopkeeper/product/{productid}")
    Call<Product> removeProduct(@Path("productid") long productid);
}
