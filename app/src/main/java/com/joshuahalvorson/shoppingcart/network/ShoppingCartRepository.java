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


    public static List<Product> getAllProducts(){
        Call<List<Product>> call = client.getAllProducts();
        try {
            Response<List<Product>> response = call.execute();
            return response.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Product> getAllActiveProducts(){
        Call<List<Product>> call = client.getAllProducts();
        try {
            List<Product> sortedList = new ArrayList<>();
            Response<List<Product>> response = call.execute();
            for(int i = response.body().size() - 1; i >= 0; i--){
                if(response.body().get(i).isProductActive()){
                    sortedList.add(response.body().get(i));
                }
            }
            return sortedList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Cart> getCartProducts(){
        Call<List<Cart>> call = client.getCartProducts();
        try {
            Response<List<Cart>> response = call.execute();
            return response.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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

}
