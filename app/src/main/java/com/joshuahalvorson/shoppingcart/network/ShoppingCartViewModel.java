package com.joshuahalvorson.shoppingcart.network;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import com.joshuahalvorson.shoppingcart.model.Cart;
import com.joshuahalvorson.shoppingcart.model.Order;
import com.joshuahalvorson.shoppingcart.model.OrderProductQuantity;
import com.joshuahalvorson.shoppingcart.model.Product;
import com.joshuahalvorson.shoppingcart.model.Shopper;
import com.joshuahalvorson.shoppingcart.model.Supplier;
import java.util.List;
import retrofit2.Callback;

public class ShoppingCartViewModel extends ViewModel {

    public void getAllProducts(Callback<List<Product>> callback) {
        ShoppingCartRepository.getAllProducts(callback);
    }

    public void getCartProducts(Callback<List<Cart>> callback) {
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

    public void updateProduct(Product product, long productid, Callback<Product> callback) {
        ShoppingCartRepository.updateProduct(product, productid, callback);
    }

    public void removeProduct(long productid, Callback<Product> callback) {
        ShoppingCartRepository.removeProduct(productid, callback);
    }

    public void getAllOrders(Callback<List<Order>> callback) {
        ShoppingCartRepository.getAllOrders(callback);
    }

    public void getAllSuppliers(Callback<List<Supplier>> callback) {
        ShoppingCartRepository.getAllSuppliers(callback);
    }

    public void updateSupplier(Supplier supplier, long supplierid, Callback<Supplier> callback) {
        ShoppingCartRepository.updateSupplier(supplier, supplierid, callback);
    }

    public void getAllShoppers(String key, Callback<List<Shopper>> callback) {
        ShoppingCartRepository.getAllShoppers(key, callback);
    }

    public void updateShopper(String key, Shopper shopper, long shopperid, Callback<Shopper> callback) {
        ShoppingCartRepository.updateShopper(key, shopper, shopperid, callback);
    }

    public OrderProductQuantity addOrderProductQuantity(long orderid, long productid, int quantity) {
        return ShoppingCartRepository.addOrderProductQuantity(orderid, productid, quantity);
    }

    public LiveData<List<OrderProductQuantity>> getOrderProductQuantity(long orderid) {
        return ShoppingCartRepository.getOrderProductQuantity(orderid);
    }

}
