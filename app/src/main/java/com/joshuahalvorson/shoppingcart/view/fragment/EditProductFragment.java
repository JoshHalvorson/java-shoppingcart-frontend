package com.joshuahalvorson.shoppingcart.view.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.joshuahalvorson.shoppingcart.R;
import com.joshuahalvorson.shoppingcart.model.Product;

public class EditProductFragment extends Fragment {
    private EditText productName, productDescription, productCost, productOnHand;

    public EditProductFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_product, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        productName = view.findViewById(R.id.product_name_edit_text);
        productDescription = view.findViewById(R.id.product_desc_edit_text);
        productCost = view.findViewById(R.id.product_cost_edit_text);
        productOnHand = view.findViewById(R.id.product_on_hand_edit_text);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Product product = (Product) getArguments().getSerializable("product");

        productName.setText(product.getProductName());
        productDescription.setText(product.getProductDescription());
        productCost.setText(Double.toString(product.getProductCost()));
        productOnHand.setText(Integer.toString(product.getProductOnHand()));

    }
}
