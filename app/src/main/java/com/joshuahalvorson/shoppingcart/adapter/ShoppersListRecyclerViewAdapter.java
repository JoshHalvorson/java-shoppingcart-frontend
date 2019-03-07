package com.joshuahalvorson.shoppingcart.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.joshuahalvorson.shoppingcart.R;
import com.joshuahalvorson.shoppingcart.model.Order;
import com.joshuahalvorson.shoppingcart.model.Product;
import com.joshuahalvorson.shoppingcart.model.Shopper;

import java.util.List;

public class ShoppersListRecyclerViewAdapter extends
        RecyclerView.Adapter<ShoppersListRecyclerViewAdapter.ViewHolder> {

    private final List<Shopper> shoppers;

    private FragmentActivity activity;
    private AppCompatActivity appCompatActivity;

    public ShoppersListRecyclerViewAdapter(List<Shopper> shoppers,
                                         FragmentActivity activity,
                                         AppCompatActivity appCompatActivity) {
        this.shoppers = shoppers;
        this.activity = activity;
        this.appCompatActivity = appCompatActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.shopper_list_element_layout, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        final Shopper shopper = shoppers.get(i);

        viewHolder.shopperName.setText(shopper.getShopperName());
        viewHolder.shopperShipping.setText("Shipping address: " + shopper.getShopperShippingAddress());
        viewHolder.shopperBilling.setText("Billing address: " + shopper.getShopperBillingAddress());
        viewHolder.shopperPayment.setText("Payment method: " + shopper.getShopperPaymentMethod());
        viewHolder.shopperPhone.setText("Phone: " + shopper.getShopperPhoneNumber());
    }

    @Override
    public int getItemCount() {
        return shoppers.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final View view;
        private final TextView shopperName, shopperShipping, shopperBilling, shopperPayment, shopperPhone;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            this.shopperName = view.findViewById(R.id.shopper_name);
            this.shopperShipping = view.findViewById(R.id.shopper_shipping);
            this.shopperBilling = view.findViewById(R.id.shopper_billing);
            this.shopperPayment = view.findViewById(R.id.shopper_payment);
            this.shopperPhone = view.findViewById(R.id.shopper_phone);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}