package com.joshuahalvorson.shoppingcart.view.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.joshuahalvorson.shoppingcart.R;
import com.joshuahalvorson.shoppingcart.adapter.CartListRecyclerViewAdapter;
import com.joshuahalvorson.shoppingcart.adapter.ProductsListRecyclerViewAdapter;
import com.joshuahalvorson.shoppingcart.model.Cart;
import com.joshuahalvorson.shoppingcart.model.Product;
import com.joshuahalvorson.shoppingcart.network.ShoppingCartViewModel;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment {
    private ShoppingCartViewModel viewModel;

    private List<Product> products;
    private List<Cart> carts;

    private RecyclerView recyclerView;
    private FloatingActionButton placeOrderButton;

    private CartListRecyclerViewAdapter adapter;

    public CartFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        products = new ArrayList<>();
        carts = new ArrayList<>();

        placeOrderButton = view.findViewById(R.id.place_order_button);

        recyclerView = view.findViewById(R.id.cart_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext()) {
            @Override
            public boolean canScrollVertically() {
                return true;
            }
        };
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(
                        recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        adapter = new CartListRecyclerViewAdapter(
                products,
                getActivity(),
                (AppCompatActivity)getActivity(),
                carts);

        recyclerView.setAdapter(adapter);
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
                for(Cart c : productsInCart){
                    for(Product p : productsList){
                        if(c.getProductId() == p.getProductId()){
                            products.add(p);
                            carts.add(c);
                            Log.i("productsincart", p.getProductName() + " " + c.getQuantity().toString());
                        }
                    }
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();

        placeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.add(R.id.fragment_container, new PlaceOrderFragment(), "placeorderfragment");
                ft.addToBackStack(null);
                ft.commit();
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
