package com.joshuahalvorson.shoppingcart.network;

import android.arch.lifecycle.ViewModel;

import com.joshuahalvorson.shoppingcart.model.Product;

import java.util.List;

public class ShoppingCartViewModel extends ViewModel {
    public List<Product> getAllProducts(){
        return ShoppingCartRepository.getAllProducts();
    }

    public List<Product> getCartProducts(){
        return ShoppingCartRepository.getCartProducts();
    }
}
