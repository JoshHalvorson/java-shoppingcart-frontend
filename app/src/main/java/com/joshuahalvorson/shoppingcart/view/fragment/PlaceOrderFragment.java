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
import android.widget.Spinner;
import android.widget.TextView;

import com.joshuahalvorson.shoppingcart.R;
import com.joshuahalvorson.shoppingcart.model.Cart;
import com.joshuahalvorson.shoppingcart.model.Order;
import com.joshuahalvorson.shoppingcart.model.Product;
import com.joshuahalvorson.shoppingcart.model.Shopper;
import com.joshuahalvorson.shoppingcart.network.ShoppingCartViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    private Spinner paymentMethodSpinner;

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
        paymentMethodSpinner = view.findViewById(R.id.customer_payment);

        confirmOrderButton = view.findViewById(R.id.confirm_order_button);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(ShoppingCartViewModel.class);

        final List<Cart> productsInCart = new ArrayList<>();
        final List<Product> productsList = new ArrayList<>();

        viewModel.getAllProducts(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                productsList.addAll(response.body());
                viewModel.getCartProducts(new Callback<List<Cart>>() {
                    @Override
                    public void onResponse(Call<List<Cart>> call, Response<List<Cart>> response) {
                        productsInCart.addAll(response.body());
                        for (final Cart c : productsInCart) {
                            for (final Product p : productsList) {
                                if (c.getProductId() == p.getProductId()) {
                                    products.add(p);
                                    carts.add(c);
                                    cost += (c.getQuantity() * p.getProductCost());
                                    TextView textView = new TextView(getContext());
                                    textView.setText(p.getProductName() + " (" + c.getQuantity() + ") " +
                                            "$" + p.getProductCost() + "/each");
                                    textView.setTextSize(18);
                                    textView.setTextColor(Color.BLACK);
                                    orderList.addView(textView);
                                    costText.setText("Total cost: $" + Double.toString(cost));
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Cart>> call, Throwable t) {

                    }
                });

            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });

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

                    final Shopper shopper = new Shopper();
                    shopper.setShopperName(nameText.getText().toString());
                    shopper.setShopperShippingAddress(shippingText.getText().toString());
                    shopper.setShopperBillingAddress(billingText.getText().toString());
                    shopper.setShopperPhoneNumber(phoneText.getText().toString());
                    shopper.setShopperPaymentMethod(paymentMethodSpinner.getSelectedItem().toString());
                    
                    final Order order = new Order();
                    order.setProducts(products);
                    order.setOrderShippingAddress(shopper.getShopperShippingAddress());
                    order.setOrderPaymentMethod(shopper.getShopperPaymentMethod());
                    order.setOrderShipped(false);

                    //save info to db
                    viewModel.addShopper(shopper, new Callback<Shopper>() {
                        @Override
                        public void onResponse(Call<Shopper> call, Response<Shopper> response) {
                            Log.i("addedshopper", shopper.getShopperName() + " added");
                            viewModel.addOrder(order, new Callback<Order>() {
                                @Override
                                public void onResponse(Call<Order> call, Response<Order> response) {
                                    Log.i("addedorder", "added");
                                }

                                @Override
                                public void onFailure(Call<Order> call, Throwable t) {

                                }
                            });
                        }

                        @Override
                        public void onFailure(Call<Shopper> call, Throwable t) {

                        }
                    });
                    Log.i("placedorder", "order has been placed");
                    getFragmentManager().popBackStack();
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
