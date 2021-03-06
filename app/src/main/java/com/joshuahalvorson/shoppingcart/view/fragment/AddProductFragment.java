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
import android.widget.EditText;
import android.widget.Toast;
import com.joshuahalvorson.shoppingcart.R;
import com.joshuahalvorson.shoppingcart.model.Product;
import com.joshuahalvorson.shoppingcart.network.ShoppingCartViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddProductFragment extends Fragment {
    private EditText productName, productDescription, productCost, productOnHand;
    private Button submitButton;
    private CheckBox switchActive;
    private ShoppingCartViewModel viewModel;

    public AddProductFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_product, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Add Product");

        productName = view.findViewById(R.id.product_name_edit_text);
        productDescription = view.findViewById(R.id.product_desc_edit_text);
        productCost = view.findViewById(R.id.product_cost_edit_text);
        productOnHand = view.findViewById(R.id.product_on_hand_edit_text);

        submitButton = view.findViewById(R.id.submit_button);
        switchActive = view.findViewById(R.id.switch_active_check);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel = ViewModelProviders.of(getActivity()).get(ShoppingCartViewModel.class);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Product newProduct = new Product(productName.getText().toString(),
                        productDescription.getText().toString(),
                        Double.parseDouble(productCost.getText().toString()),
                        Integer.parseInt(productOnHand.getText().toString()),
                        0,
                        switchActive.isChecked());

                viewModel.addProduct(newProduct, new Callback<Product>() {
                    @Override
                    public void onResponse(Call<Product> call, Response<Product> response) {
                        Toast.makeText(
                                getContext(), "Added " + newProduct.getProductName(),
                                Toast.LENGTH_LONG).show();
                        getFragmentManager().popBackStack();
                    }

                    @Override
                    public void onFailure(Call<Product> call, Throwable t) {
                        Toast.makeText(
                                getContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}
