package com.joshuahalvorson.shoppingcart.view;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.joshuahalvorson.shoppingcart.R;
import com.joshuahalvorson.shoppingcart.view.fragment.EditProductsFragment;
import com.joshuahalvorson.shoppingcart.view.fragment.EditSuppliersFragment;
import com.joshuahalvorson.shoppingcart.view.fragment.ManageUsersFragment;
import com.joshuahalvorson.shoppingcart.view.fragment.OrdersFragment;
import com.joshuahalvorson.shoppingcart.view.fragment.ShopFragment;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener,
        ShopFragment.OnFragmentInteractionListener {

    FragmentManager fragmentManager;
    FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentManager = getSupportFragmentManager();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_shop) {
            ft = fragmentManager.beginTransaction();
            ft.replace(R.id.fragment_container, new ShopFragment(), "shopfragment");
            ft.addToBackStack(null);
            ft.commit();
        } else if (id == R.id.nav_shopkeep_products) {
            ft = fragmentManager.beginTransaction();
            ft.replace(R.id.fragment_container, new EditProductsFragment(), "editproductsfragment");
            ft.addToBackStack(null);
            ft.commit();
        } else if (id == R.id.nav_shopkeep_orders) {
            ft = fragmentManager.beginTransaction();
            ft.replace(R.id.fragment_container, new OrdersFragment(), "ordersfragment");
            ft.addToBackStack(null);
            ft.commit();
        } else if (id == R.id.nav_shopkeep_suppliers) {
            ft = fragmentManager.beginTransaction();
            ft.replace(R.id.fragment_container, new EditSuppliersFragment(), "editsuppliersfragment");
            ft.addToBackStack(null);
            ft.commit();
        } else if (id == R.id.nav_admin_users) {
            ft = fragmentManager.beginTransaction();
            ft.replace(R.id.fragment_container, new ManageUsersFragment(), "manageusersfragment");
            ft.addToBackStack(null);
            ft.commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onShopFragmentInteraction(Uri uri) {

    }
}
