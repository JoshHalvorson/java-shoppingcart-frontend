package com.joshuahalvorson.shoppingcart.adapter;

import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import com.joshuahalvorson.shoppingcart.R;
import com.joshuahalvorson.shoppingcart.model.Cart;
import com.joshuahalvorson.shoppingcart.model.Product;
import com.joshuahalvorson.shoppingcart.network.ShoppingCartViewModel;
import java.util.ArrayList;
import java.util.List;


public class CartListRecyclerViewAdapter  extends
        RecyclerView.Adapter<CartListRecyclerViewAdapter.ViewHolder>{

    private final List<Product> productList;

    private ShoppingCartViewModel viewModel;

    private FragmentActivity activity;
    private AppCompatActivity appCompatActivity;

    private List<Cart> carts;

    public CartListRecyclerViewAdapter(List<Product> productList,
                                       FragmentActivity activity,
                                       AppCompatActivity appCompatActivity,
                                       List<Cart> carts) {
        this.productList = productList;
        this.activity = activity;
        this.appCompatActivity = appCompatActivity;
        this.carts = carts;
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
        final Cart cart = carts.get(i);

        viewModel = ViewModelProviders.of(activity).get(ShoppingCartViewModel.class);

        viewHolder.productName.setText(product.getProductName());
        viewHolder.productDesc.setText(product.getProductDescription());
        viewHolder.productCost.setText("$" + Double.toString(product.getProductCost()));
        viewHolder.productOnHand.setText(Integer.toString(product.getProductOnHand()) + " unit(s) left");

        List<String> spinnerArray =  new ArrayList<>();
        spinnerArray.add(cart.getQuantity().toString());

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>
                (activity, android.R.layout.simple_spinner_item, spinnerArray);

        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        viewHolder.quantitySpinner.setAdapter(spinnerAdapter);

        viewHolder.addButton.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final View view;
        private TextView productName, productDesc, productCost, productOnHand;
        private Spinner quantitySpinner;
        private Button addButton;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            productName = view.findViewById(R.id.product_name);
            productCost = view.findViewById(R.id.product_cost);
            productDesc = view.findViewById(R.id.product_desc);
            productOnHand = view.findViewById(R.id.product_on_hand);
            quantitySpinner = view.findViewById(R.id.number_to_add_spinner);
            addButton = view.findViewById(R.id.add_button);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}
