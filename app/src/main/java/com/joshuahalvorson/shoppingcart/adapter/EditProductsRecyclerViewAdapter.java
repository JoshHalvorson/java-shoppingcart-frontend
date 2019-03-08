package com.joshuahalvorson.shoppingcart.adapter;

import android.arch.lifecycle.ViewModelProviders;
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
import android.widget.Spinner;
import android.widget.TextView;
import com.joshuahalvorson.shoppingcart.R;
import com.joshuahalvorson.shoppingcart.model.Product;
import com.joshuahalvorson.shoppingcart.network.ShoppingCartViewModel;
import com.joshuahalvorson.shoppingcart.view.fragment.EditProductFragment;
import java.util.List;

public class EditProductsRecyclerViewAdapter extends
        RecyclerView.Adapter<EditProductsRecyclerViewAdapter.ViewHolder> {

    private final List<Product> productList;

    private ShoppingCartViewModel viewModel;

    FragmentActivity activity;
    AppCompatActivity appCompatActivity;

    public EditProductsRecyclerViewAdapter(List<Product> productList,
                                           FragmentActivity activity,
                                           AppCompatActivity appCompatActivity) {
        this.productList = productList;
        this.activity = activity;
        this.appCompatActivity = appCompatActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.product_list_element_layout, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        final Product product = productList.get(i);

        viewModel = ViewModelProviders.of(activity).get(ShoppingCartViewModel.class);

        viewHolder.productName.setText(product.getProductName());
        viewHolder.productDesc.setText(product.getProductDescription());
        viewHolder.productCost.setText("$" + Double.toString(product.getProductCost()));
        viewHolder.productOnHand.setText(Integer.toString(product.getProductOnHand()) + " unit(s) left");

        viewHolder.quantitySpinner.setVisibility(View.GONE);

        viewHolder.editButton.setBackground(activity.getResources()
                .getDrawable(R.drawable.ic_edit_black_24dp));
        viewHolder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
                EditProductFragment editProductFragment = new EditProductFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("product", product);
                editProductFragment.setArguments(bundle);
                ft.replace(R.id.fragment_container, editProductFragment, "editproductfragment");
                ft.addToBackStack(null);
                ft.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final View view;
        private TextView productName, productDesc, productCost, productOnHand;
        private Spinner quantitySpinner;
        private Button editButton;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            productName = view.findViewById(R.id.product_name);
            productCost = view.findViewById(R.id.product_cost);
            productDesc = view.findViewById(R.id.product_desc);
            productOnHand = view.findViewById(R.id.product_on_hand);
            quantitySpinner = view.findViewById(R.id.number_to_add_spinner);
            editButton = view.findViewById(R.id.action_button);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}
