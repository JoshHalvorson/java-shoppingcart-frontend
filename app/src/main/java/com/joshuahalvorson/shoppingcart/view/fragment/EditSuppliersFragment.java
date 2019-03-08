package com.joshuahalvorson.shoppingcart.view.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.joshuahalvorson.shoppingcart.R;
import com.joshuahalvorson.shoppingcart.adapter.SuppliersListRecyclerViewAdapter;
import com.joshuahalvorson.shoppingcart.model.Supplier;
import com.joshuahalvorson.shoppingcart.network.ShoppingCartViewModel;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditSuppliersFragment extends Fragment {

    private ShoppingCartViewModel viewModel;

    private RecyclerView recyclerView;
    private SuppliersListRecyclerViewAdapter adapter;

    private List<Supplier> suppliers;

    public EditSuppliersFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_suppliers, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        suppliers = new ArrayList<>();

        recyclerView = view.findViewById(R.id.suppliers_list);
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

        adapter = new SuppliersListRecyclerViewAdapter(
                suppliers,
                getActivity(),
                (AppCompatActivity) getActivity());

        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel = ViewModelProviders.of(getActivity()).get(ShoppingCartViewModel.class);

        viewModel.getAllSuppliers(new Callback<List<Supplier>>() {
            @Override
            public void onResponse(Call<List<Supplier>> call, Response<List<Supplier>> response) {
                suppliers.addAll(response.body());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Supplier>> call, Throwable t) {
                Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
