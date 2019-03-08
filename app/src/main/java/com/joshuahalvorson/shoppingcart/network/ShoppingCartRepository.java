package com.joshuahalvorson.shoppingcart.network;

import android.arch.lifecycle.MutableLiveData;
import com.joshuahalvorson.shoppingcart.model.Cart;
import com.joshuahalvorson.shoppingcart.model.Order;
import com.joshuahalvorson.shoppingcart.model.OrderProductQuantity;
import com.joshuahalvorson.shoppingcart.model.Product;
import com.joshuahalvorson.shoppingcart.model.Shopper;
import com.joshuahalvorson.shoppingcart.model.Supplier;
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


    public static void getAllProducts(Callback<List<Product>> callback) {
        Call<List<Product>> call = client.getAllProducts();
        call.enqueue(callback);
    }

    public static void getCartProducts(Callback<List<Cart>> callback) {
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

    public static void updateProduct(Product product, long productid, Callback<Product> callback) {
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

    public static void getAllSuppliers(Callback<List<Supplier>> callback) {
        Call<List<Supplier>> call = client.getAllSuppliers();
        call.enqueue(callback);
    }

    public static void updateSupplier(Supplier supplier, long supplierid, Callback<Supplier> callback) {
        Call<Supplier> call = client.updateSupplier(supplier, supplierid);
        call.enqueue(callback);
    }

    public static void getAllShoppers(String key, Callback<List<Shopper>> callback) {
        Call<List<Shopper>> call = client.getAllShoppers(key);
        call.enqueue(callback);
    }

    public static void updateShopper(String key, Shopper shopper, long shopperid, Callback<Shopper> callback) {
        Call<Shopper> call = client.updateShopper(key, shopper, shopperid);
        call.enqueue(callback);
    }

    public static OrderProductQuantity addOrderProductQuantity(long orderid, long productid, int quantity) {
        Call<OrderProductQuantity> call = client.addOrderProductQuantity(orderid, productid, quantity);
        try {
            return call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static MutableLiveData<List<OrderProductQuantity>> getOrderProductQuantity(long orderid) {
        Call<List<OrderProductQuantity>> call = client.getOrderProductQuantity(orderid);
        final MutableLiveData<List<OrderProductQuantity>> data = new MutableLiveData<>();
        call.enqueue(new Callback<List<OrderProductQuantity>>() {
            @Override
            public void onResponse(Call<List<OrderProductQuantity>> call, Response<List<OrderProductQuantity>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<OrderProductQuantity>> call, Throwable t) {

            }
        });
        return data;
    }

}
