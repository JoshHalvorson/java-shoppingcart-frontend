package com.joshuahalvorson.shoppingcart.view;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.joshuahalvorson.shoppingcart.R;
import com.joshuahalvorson.shoppingcart.model.Product;
import com.joshuahalvorson.shoppingcart.network.ShoppingCartViewModel;
import java.util.List;

public class ShopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        final ShoppingCartViewModel viewModel = ViewModelProviders.of(this).get(ShoppingCartViewModel.class);


        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Product> products = viewModel.getAllProducts();
                for(Product p : products){
                    Log.i("product", p.getProductName());
                }
            }
        }).start();

    }


}
