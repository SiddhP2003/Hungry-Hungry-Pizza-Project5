package com.example.project5;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.project5.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    //private ActivityMainBinding binding;
    BottomNavigationView navView;
    ChicagoStyle chicagoStyle = new ChicagoStyle();
    NewYorkStyle newYorkStyle = new NewYorkStyle();
    CurrentOrder currentOrder = new CurrentOrder();
    StoreOrders storeOrders = new StoreOrders();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //binding = ActivityMainBinding.inflate(getLayoutInflater());
        //setContentView(binding.getRoot());

        navView = (BottomNavigationView)findViewById(R.id.nav_view);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, chicagoStyle);
        navView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.chicagoStyle:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, chicagoStyle).commit();
                        return true;
                    case R.id.newYorkStyle:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, newYorkStyle).commit();
                        return true;
                    case R.id.currentOrder:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, currentOrder).commit();
                        return true;
                    case R.id.storeOrders:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, storeOrders).commit();
                        return true;
                }
                return false;
            }
        });
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_chicago, R.id.navigation_newyork, R.id.navigation_current, R.id.navigation_store)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(binding.navView, navController);


    }

}