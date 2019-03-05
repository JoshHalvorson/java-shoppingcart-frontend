package com.joshuahalvorson.shoppingcart;

import android.arch.lifecycle.MutableLiveData;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.joshuahalvorson.shoppingcart.model.Product;
import com.joshuahalvorson.shoppingcart.network.ShoppingCartClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(ShoppingCartClient.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private static ShoppingCartClient client = retrofit.create(ShoppingCartClient.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread(new Runnable() {
            @Override
            public void run() {
                createProduct(new Product("Cookies", "A dozen cookies", 10.00, 200));
                /*List<Product> productList = getAllProducts();
                for(Product p : productList){
                    Log.i("product", p.getProductName());
                }*/
            }
        }).start();

    }

    public Product createProduct(Product product){
        Gson gson = new Gson();
        client.addProduct(gson.toJson(product));
        return product;
    }

    public List<Product> getAllProducts(){
        Call<List<Product>> call = client.getAllProducts();
        try {
            Response<List<Product>> response = call.execute();
            return response.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
