package com.joshuahalvorson.shoppingcart.network;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.joshuahalvorson.shoppingcart.model.Cart;
import com.joshuahalvorson.shoppingcart.model.Order;
import com.joshuahalvorson.shoppingcart.model.Product;
import com.joshuahalvorson.shoppingcart.model.Shopper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShoppingCartRepository {
    private static final String TAG = "ShoppingCartRepository";

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(ShoppingCartClient.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private static ShoppingCartClient client = retrofit.create(ShoppingCartClient.class);


    public static void getAllProducts(Callback<List<Product>> callback) {
        Call<List<Product>> call = client.getAllProducts();
        call.enqueue(callback);
    }

    public static void getCartProducts(Callback<List<Cart>> callback){
        Call<List<Cart>> call = client.getCartProducts();
        call.enqueue(callback);
    }

    public static void addProduct(Product product, Callback<Product> callback) {
        Call<Product> call = client.addProduct(product);
        call.enqueue(callback);
    }

    public static void addProductToCart(int quantity, long productid, Callback<Product> callback) {
        Call<Product> call = client.addProductToCart(quantity, productid);
        call.enqueue(callback);
    }

    public static void removeProductFromCart(long productid, Callback<Product> callback) {
        Call<Product> call = client.removeProductFromCart(productid);
        call.enqueue(callback);
    }

    public static void addShopper(Shopper shopper, Callback<Shopper> callback) {
        Call<Shopper> call = client.addShopper(shopper);
        call.enqueue(callback);
    }

    public static void addOrder(Order order, Callback<Order> callback) {
        Call<Order> call = client.addOrder(order);
        call.enqueue(callback);
    }

    public static void updateProduct(Product product, long productid, Callback<Product> callback){
        Call<Product> call = client.updateProduct(product, productid);
        call.enqueue(callback);
    }

    public static void removeProduct(long productid, Callback<Product> callback) {
        Call<Product> call = client.removeProduct(productid);
        call.enqueue(callback);
    }

    public static void getAllOrders(Callback<List<Order>> callback) {
        Call<List<Order>> call = client.getAllOrders();
        call.enqueue(callback);
    }

}
