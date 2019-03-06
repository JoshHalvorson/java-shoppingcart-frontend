package com.joshuahalvorson.shoppingcart.view.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joshuahalvorson.shoppingcart.R;
import com.joshuahalvorson.shoppingcart.model.Cart;
import com.joshuahalvorson.shoppingcart.model.Product;
import com.joshuahalvorson.shoppingcart.network.ShoppingCartViewModel;

import java.util.ArrayList;
import java.util.List;

public class PlaceOrderFragment extends Fragment {
    private ShoppingCartViewModel viewModel;

    private List<Product> products;
    private List<Cart> carts;

    private LinearLayout orderList;

    private double cost;
    private TextView costText;
    private CheckBox sameAsBilling;

    private EditText shippingText, billingText, nameText, phoneText;

    private FloatingActionButton confirmOrderButton;

    public PlaceOrderFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        products = new ArrayList<>();
        carts = new ArrayList<>();

        orderList = view.findViewById(R.id.order_list);
        costText = view.findViewById(R.id.price_text);
        sameAsBilling = view.findViewById(R.id.same_as_billing_check);

        shippingText = view.findViewById(R.id.customer_shipping);
        billingText = view.findViewById(R.id.customer_billing);
        nameText = view.findViewById(R.id.customer_name);
        phoneText = view.findViewById(R.id.customer_phone);

        confirmOrderButton = view.findViewById(R.id.confirm_order_button);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(ShoppingCartViewModel.class);
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Cart> productsInCart = viewModel.getCartProducts();
                List<Product> productsList = viewModel.getAllProducts();
                for (final Cart c : productsInCart) {
                    for (final Product p : productsList) {
                        if (c.getProductId() == p.getProductId()) {
                            products.add(p);
                            carts.add(c);
                            cost += (c.getQuantity() * p.getProductCost());
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    TextView textView = new TextView(getContext());
                                    textView.setText(p.getProductName() + " (" + c.getQuantity() + ") " +
                                            "$" + p.getProductCost() + "/each");
                                    textView.setTextSize(18);
                                    textView.setTextColor(Color.BLACK);
                                    orderList.addView(textView);
                                    costText.setText("Total cost: $" + Double.toString(cost));
                                }
                            });
                        }
                    }
                }
            }
        }).start();

        sameAsBilling.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    shippingText.setText(billingText.getText().toString());
                }else{
                    shippingText.setText("");
                }
            }
        });

        confirmOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((!nameText.getText().toString().equals("")) &&
                        (!shippingText.getText().toString().equals("")) &&
                        (!billingText.getText().toString().equals("")) &&
                        (!phoneText.getText().toString().equals(""))) {

                    //save info to db
                    Log.i("placedorder", "order has been placed");
                }
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
