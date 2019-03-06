package com.joshuahalvorson.shoppingcart.network;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.joshuahalvorson.shoppingcart.model.Product;

import java.io.IOException;
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

    public static List<Product> getCartProducts(){
        Call<List<Product>> call = client.getCartProducts();
        try {
            Response<List<Product>> response = call.execute();
            return response.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
