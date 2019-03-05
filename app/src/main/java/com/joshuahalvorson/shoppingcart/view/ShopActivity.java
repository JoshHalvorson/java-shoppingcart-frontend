package com.joshuahalvorson.shoppingcart.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.joshuahalvorson.shoppingcart.R;
import com.joshuahalvorson.shoppingcart.model.Product;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class ShopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
    }


}
