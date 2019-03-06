package com.joshuahalvorson.shoppingcart.network;

import android.arch.lifecycle.ViewModel;

import com.joshuahalvorson.shoppingcart.model.Cart;
import com.joshuahalvorson.shoppingcart.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class ShoppingCartViewModel extends ViewModel {
    public List<Product> getAllProducts(){
        return ShoppingCartRepository.getAllProducts();
    }

    public List<Cart> getCartProducts(){
        return ShoppingCartRepository.getCartProducts();
    }

    public void addProduct(Product product, Callback<Product> callback) {
        ShoppingCartRepository.addProduct(product, callback);
    }

    public void addProductToCart(int quantity, long productid, Callback<Product> callback) {
        ShoppingCartRepository.addProductToCart(quantity, productid, callback);
    }

    public void removeProductFromCart(long productid, Callback<Product> callback) {
        ShoppingCartRepository.removeProductFromCart(productid, callback);
    }

}
