package com.joshuahalvorson.shoppingcart.view.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.joshuahalvorson.shoppingcart.R;
import com.joshuahalvorson.shoppingcart.model.Product;
import com.joshuahalvorson.shoppingcart.model.Supplier;
import com.joshuahalvorson.shoppingcart.network.ShoppingCartViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditSupplierFragment extends Fragment {
    private EditText supplierName;
    private Button submitButton;

    private LinearLayout checkboxesContainer;

    private ShoppingCartViewModel viewModel;

    private List<Product> allProducts;

    public EditSupplierFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_supplier, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        supplierName = view.findViewById(R.id.supplier_name_edit_text);

        submitButton = view.findViewById(R.id.submit_button);

        checkboxesContainer = view.findViewById(R.id.supplier_product_list);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        allProducts = new ArrayList<>();

        viewModel = ViewModelProviders.of(getActivity()).get(ShoppingCartViewModel.class);

        viewModel.getAllProducts(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                allProducts.addAll(response.body());
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });

        final Supplier supplier = (Supplier) getArguments().getSerializable("supplier");

        supplierName.setText(supplier.getSupplierName());

        final List<Product> productsInSupplier = supplier.getSupplierProducts();

        viewModel.getAllProducts(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product> products = response.body();
                for(int i = 0; i < products.size(); i++){
                    CheckBox checkBox = new CheckBox(getContext());
                    checkBox.setText(products.get(i).getProductName());
                    for(int j = 0; j < productsInSupplier.size(); j++){
                        if(products.get(i).getProductName().equals(productsInSupplier.get(j).getProductName())){
                            checkBox.setChecked(true);
                        }
                    }
                    checkboxesContainer.addView(checkBox);
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<CheckBox> checkboxes = new ArrayList<>();
                List<Product> newProducts = new ArrayList<>();

                for(int i = 0; i < checkboxesContainer.getChildCount(); i++){
                    checkboxes.add((CheckBox) checkboxesContainer.getChildAt(i));
                }

                for(CheckBox c : checkboxes){
                    if(c.isChecked()){
                        for(Product p : allProducts){
                            if(c.getText().toString().equals(p.getProductName())){
                                newProducts.add(p);
                            }
                        }
                    }
                }

                final Supplier newSupplier = new Supplier(supplierName.getText().toString(),
                        newProducts);

                viewModel.updateSupplier(newSupplier, supplier.getSupplierId(), new Callback<Supplier>() {
                    @Override
                    public void onResponse(Call<Supplier> call, Response<Supplier> response) {
                        Toast.makeText(
                                getContext(), "Edited " + newSupplier.getSupplierName(),
                                Toast.LENGTH_LONG).show();
                        getFragmentManager().popBackStack();
                    }

                    @Override
                    public void onFailure(Call<Supplier> call, Throwable t) {
                        Toast.makeText(
                                getContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}