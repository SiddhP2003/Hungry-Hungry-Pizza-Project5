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
    ChicagoStyle chicagoStyleFragment = new ChicagoStyle();
    NewYorkStyle newYorkStyleFragment = new NewYorkStyle();
    CurrentOrder currentOrderFragment = new CurrentOrder();
    StoreOrders storeOrdersFragment = new StoreOrders();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //binding = ActivityMainBinding.inflate(getLayoutInflater());
        //setContentView(binding.getRoot());

        navView = findViewById(R.id.nav_view);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, chicagoStyleFragment).commit();
        navView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.navigation_chicago:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, chicagoStyleFragment).commit();
                        return true;
                    case R.id.navigation_newyork:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, newYorkStyleFragment).commit();
                        return true;
                    case R.id.navigation_current:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, currentOrderFragment).commit();
                        return true;
                    case R.id.navigation_store:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, storeOrdersFragment).commit();
                        return true;
                }
                return false;
            }
        });


    }

}