package com.joshuahalvorson.shoppingcart.network;

import android.arch.lifecycle.ViewModel;

import com.joshuahalvorson.shoppingcart.model.Cart;
import com.joshuahalvorson.shoppingcart.model.Order;
import com.joshuahalvorson.shoppingcart.model.Product;
import com.joshuahalvorson.shoppingcart.model.Shopper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class ShoppingCartViewModel extends ViewModel {

    public void getAllProducts(Callback<List<Product>> callback){
        ShoppingCartRepository.getAllProducts(callback);
    }

    public void getCartProducts(Callback<List<Cart>> callback){
        ShoppingCartRepository.getCartProducts(callback);
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

    public void addShopper(Shopper shopper, Callback<Shopper> callback) {
        ShoppingCartRepository.addShopper(shopper, callback);
    }

    public void addOrder(Order order, Callback<Order> callback) {
        ShoppingCartRepository.addOrder(order, callback);
    }

    public void updateProduct(Product product, long productid, Callback<Product> callback){
        ShoppingCartRepository.updateProduct(product, productid, callback);
    }

    public void removeProduct(long productid, Callback<Product> callback) {
        ShoppingCartRepository.removeProduct(productid, callback);
    }

    public void getAllOrders(Callback<List<Order>> callback){
        ShoppingCartRepository.getAllOrders(callback);
    }

}
