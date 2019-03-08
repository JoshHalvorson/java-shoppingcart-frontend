package com.joshuahalvorson.shoppingcart.adapter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.joshuahalvorson.shoppingcart.R;
import com.joshuahalvorson.shoppingcart.model.Shopper;
import com.joshuahalvorson.shoppingcart.view.fragment.EditShopperFragment;
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

        viewHolder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
                EditShopperFragment editShopperFragment = new EditShopperFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("shopper", shopper);
                editShopperFragment.setArguments(bundle);
                ft.replace(R.id.fragment_container, editShopperFragment, "editshopperfragment");
                ft.addToBackStack(null);
                ft.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return shoppers.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final View view;
        private final TextView shopperName, shopperShipping, shopperBilling, shopperPayment, shopperPhone;
        private final Button editButton;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            this.shopperName = view.findViewById(R.id.shopper_name);
            this.shopperShipping = view.findViewById(R.id.shopper_shipping);
            this.shopperBilling = view.findViewById(R.id.shopper_billing);
            this.shopperPayment = view.findViewById(R.id.shopper_payment);
            this.shopperPhone = view.findViewById(R.id.shopper_phone);
            this.editButton = view.findViewById(R.id.action_button);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}