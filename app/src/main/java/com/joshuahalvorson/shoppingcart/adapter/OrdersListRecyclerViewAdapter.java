package com.joshuahalvorson.shoppingcart.adapter;

import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import com.joshuahalvorson.shoppingcart.R;
import com.joshuahalvorson.shoppingcart.model.Order;
import com.joshuahalvorson.shoppingcart.model.Product;
import com.joshuahalvorson.shoppingcart.network.ShoppingCartViewModel;
import java.util.List;

public class OrdersListRecyclerViewAdapter extends
        RecyclerView.Adapter<OrdersListRecyclerViewAdapter.ViewHolder> {

    private final List<Order> orders;

    private FragmentActivity activity;
    private AppCompatActivity appCompatActivity;

    public OrdersListRecyclerViewAdapter(List<Order> orders,
                                           FragmentActivity activity,
                                           AppCompatActivity appCompatActivity) {
        this.orders = orders;
        this.activity = activity;
        this.appCompatActivity = appCompatActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.order_list_element_layout, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        final Order order = orders.get(i);

        viewHolder.orderShipping.setText("Shipping address: " + order.getOrderShippingAddress());
        viewHolder.orderPayment.setText("Payment method: " + order.getOrderPaymentMethod());
        viewHolder.orderShipped.setText("Order shipped: " + order.getOrderShipped().toString());
        for(Product p : order.getProducts()){
            viewHolder.orderProductList.setText(viewHolder.orderProductList.getText().toString() +
                    p.getProductName() + "\n");
        }
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final View view;
        private TextView orderShipping, orderPayment, orderShipped, orderProductList;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            orderShipping = view.findViewById(R.id.order_shipping);
            orderPayment = view.findViewById(R.id.order_payment);
            orderShipped = view.findViewById(R.id.order_shipped);
            orderProductList = view.findViewById(R.id.order_product_list);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}