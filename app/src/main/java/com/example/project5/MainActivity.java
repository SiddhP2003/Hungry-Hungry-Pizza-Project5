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
    public static ChicagoStyle chicagoStyle = new ChicagoStyle();
    public static NewYorkStyle newYorkStyle = new NewYorkStyle();
    public static CurrentOrder currentOrder = new CurrentOrder();
    public static StoreOrders storeOrders = new StoreOrders();
    private int currentOrderNumber = 1;
    public static Order order = new Order();
    public static StoreOrder allOrders = new StoreOrder();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //binding = ActivityMainBinding.inflate(getLayoutInflater());
        //setContentView(binding.getRoot());

        navView = (BottomNavigationView)findViewById(R.id.nav_view);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, chicagoStyle).commit();
        navView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.navigation_chicago:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, chicagoStyle).commit();
                        return true;
                    case R.id.navigation_newyork:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, newYorkStyle).commit();
                        return true;
                    case R.id.navigation_current:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, currentOrder).commit();
                        return true;
                    case R.id.navigation_store:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, storeOrders).commit();
                        return true;
                }
                return false;
            }
        });
        order.setOrderNumber(currentOrderNumber);


    }

    public void newOrder(){
        currentOrderNumber++;
        order = new Order();
        order.setOrderNumber(currentOrderNumber);
    }

    public int getOrderNumber(){
        return currentOrderNumber;
    }

    public void updateCurrentOrder(){
        currentOrder.updateCurrentPrice();
    }

}