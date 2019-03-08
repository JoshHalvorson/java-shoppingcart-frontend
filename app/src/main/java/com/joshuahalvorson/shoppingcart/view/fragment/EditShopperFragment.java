package com.joshuahalvorson.shoppingcart.view.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.joshuahalvorson.shoppingcart.R;
import com.joshuahalvorson.shoppingcart.model.Shopper;
import com.joshuahalvorson.shoppingcart.network.ShoppingCartViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditShopperFragment extends Fragment {
    private EditText shopperName, shopperShipping, shopperBilling, shopperPhone;
    private Spinner paymentMethodSpinner;
    private Button submitButton;

    private ShoppingCartViewModel viewModel;

    private CheckBox sameAsBilling;

    private static final String ADMIN_KEY = "";

    public EditShopperFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_shopper, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Edit Shopper");

        shopperShipping = view.findViewById(R.id.customer_shipping);
        shopperBilling = view.findViewById(R.id.customer_billing);
        shopperName = view.findViewById(R.id.customer_name);
        shopperPhone = view.findViewById(R.id.customer_phone);
        paymentMethodSpinner = view.findViewById(R.id.customer_payment);

        submitButton = view.findViewById(R.id.submit_button);

        sameAsBilling = view.findViewById(R.id.same_as_billing_check);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel = ViewModelProviders.of(getActivity()).get(ShoppingCartViewModel.class);

        final Shopper shopper = (Shopper) getArguments().getSerializable("shopper");

        shopperName.setText(shopper.getShopperName());
        shopperShipping.setText(shopper.getShopperShippingAddress());
        shopperBilling.setText(shopper.getShopperBillingAddress());
        shopperPhone.setText(shopper.getShopperPhoneNumber());

        sameAsBilling.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    shopperShipping.setText(shopperBilling.getText().toString());
                } else {
                    shopperShipping.setText("");
                }
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((!shopperName.getText().toString().equals("")) &&
                        (!shopperShipping.getText().toString().equals("")) &&
                        (!shopperBilling.getText().toString().equals("")) &&
                        (!shopperPhone.getText().toString().equals(""))) {

                    final Shopper newShopper = new Shopper();
                    newShopper.setShopperName(shopperName.getText().toString());
                    newShopper.setShopperShippingAddress(shopperShipping.getText().toString());
                    newShopper.setShopperBillingAddress(shopperBilling.getText().toString());
                    newShopper.setShopperPhoneNumber(shopperPhone.getText().toString());
                    newShopper.setShopperPaymentMethod(paymentMethodSpinner.getSelectedItem().toString());

                    viewModel.updateShopper(ADMIN_KEY, newShopper, shopper.getShopperId(), new Callback<Shopper>() {
                        @Override
                        public void onResponse(Call<Shopper> call, Response<Shopper> response) {
                            getFragmentManager().popBackStack();
                        }

                        @Override
                        public void onFailure(Call<Shopper> call, Throwable t) {
                            Toast.makeText(getContext(),
                                    t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                        }
                    });

                }
            }
        });

    }
}