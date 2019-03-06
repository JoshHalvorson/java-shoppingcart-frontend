package com.joshuahalvorson.shoppingcart.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.joshuahalvorson.shoppingcart.R;
import com.joshuahalvorson.shoppingcart.model.Product;
import com.joshuahalvorson.shoppingcart.view.fragment.ShopFragment;

import java.util.ArrayList;
import java.util.List;

public class ProductsListRecyclerViewAdapter extends
        RecyclerView.Adapter<ProductsListRecyclerViewAdapter.ViewHolder>{

    private final List<Product> productList;

    private ShopFragment.OnFragmentInteractionListener listener;

    FragmentActivity activity;
    AppCompatActivity appCompatActivity;

    public ProductsListRecyclerViewAdapter(List<Product> productList,
                                      ShopFragment.OnFragmentInteractionListener listener,
                                      FragmentActivity activity,
                                      AppCompatActivity appCompatActivity) {
        this.productList = productList;
        this.listener = listener;
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

        viewHolder.productName.setText(product.getProductName());
        viewHolder.productDesc.setText(product.getProductDescription());
        viewHolder.productCost.setText("$" + Double.toString(product.getProductCost()));
        viewHolder.productOnHand.setText(Integer.toString(product.getProductOnHand()) + " unit(s) left");

        List<String> spinnerArray =  new ArrayList<>();
        for(int j = 1; j <= product.getProductOnHand(); j++){
            spinnerArray.add(Integer.toString(j));
        }

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>
                (activity, android.R.layout.simple_spinner_item, spinnerArray);

        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        viewHolder.quantitySpinner.setAdapter(spinnerAdapter);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public final View view;
        TextView productName, productDesc, productCost, productOnHand;
        Spinner quantitySpinner;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            productName = view.findViewById(R.id.product_name);
            productCost = view.findViewById(R.id.product_cost);
            productDesc = view.findViewById(R.id.product_desc);
            productOnHand = view.findViewById(R.id.product_on_hand);
            quantitySpinner = view.findViewById(R.id.number_to_add_spinner);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}
