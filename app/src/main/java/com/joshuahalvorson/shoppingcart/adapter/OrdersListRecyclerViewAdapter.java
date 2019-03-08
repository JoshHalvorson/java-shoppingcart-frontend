package com.joshuahalvorson.shoppingcart.adapter;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.joshuahalvorson.shoppingcart.R;
import com.joshuahalvorson.shoppingcart.model.Order;
import com.joshuahalvorson.shoppingcart.model.OrderProductQuantity;
import com.joshuahalvorson.shoppingcart.model.Product;
import com.joshuahalvorson.shoppingcart.network.ShoppingCartViewModel;

import java.util.List;

public class OrdersListRecyclerViewAdapter extends
        RecyclerView.Adapter<OrdersListRecyclerViewAdapter.ViewHolder> {

    private final List<Order> orders;

    private ShoppingCartViewModel viewModel;

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

        viewModel = ViewModelProviders.of(activity).get(ShoppingCartViewModel.class);

        viewHolder.orderShipping.setText("Shipping address: " + order.getOrderShippingAddress());
        viewHolder.orderPayment.setText("Payment method: " + order.getOrderPaymentMethod());
        viewHolder.orderShipped.setText("Order shipped: " + order.getOrderShipped().toString());

        LiveData<List<OrderProductQuantity>> data = viewModel.getOrderProductQuantity(order.getOrderId());
        data.observe(activity, new Observer<List<OrderProductQuantity>>() {
            @Override
            public void onChanged(@Nullable List<OrderProductQuantity> orderProductQuantities) {
                for(OrderProductQuantity o : orderProductQuantities){
                    if(o.getOrderId() == order.getOrderId()){
                        for(Product p : order.getProducts()){
                            if(o.getProduct().getProductId() == p.getProductId()){
                                viewHolder.orderProductList.setText(viewHolder.orderProductList.getText().toString() +
                                        p.getProductName() +  " (" + o.getQuantity() + ") \n");
                            }
                        }
                    }
                }
            }
        });


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