package com.joshuahalvorson.shoppingcart.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.joshuahalvorson.shoppingcart.R;
import com.joshuahalvorson.shoppingcart.model.Product;
import com.joshuahalvorson.shoppingcart.model.Supplier;

import java.util.List;

public class SuppliersListRecyclerViewAdapter  extends
        RecyclerView.Adapter<SuppliersListRecyclerViewAdapter.ViewHolder> {

    private final List<Supplier> suppliers;

    private FragmentActivity activity;
    private AppCompatActivity appCompatActivity;

    public SuppliersListRecyclerViewAdapter(List<Supplier> suppliers,
                                            FragmentActivity activity,
                                            AppCompatActivity appCompatActivity) {
        this.suppliers = suppliers;
        this.activity = activity;
        this.appCompatActivity = appCompatActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.suppliers_list_element_layout, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        final Supplier supplier = suppliers.get(i);

        viewHolder.supplierName.setText("Supplier name: " + supplier.getSupplierName());
        for (Product p : supplier.getSupplierProducts()) {
            viewHolder.supplierProductList.setText(viewHolder.supplierProductList.getText().toString() +
                    p.getProductName() + "\n");
        }
    }

    @Override
    public int getItemCount() {
        return suppliers.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final View view;
        private TextView supplierName, supplierProductList;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            supplierName = view.findViewById(R.id.supplier_name);
            supplierProductList = view.findViewById(R.id.supplier_product_list);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}