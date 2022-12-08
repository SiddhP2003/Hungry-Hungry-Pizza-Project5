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

import java.util.ArrayList;

/**
 An activity that acts to set up the app.
 Sets up the bottom navigation view bar that allows the user to navigate through the app (chicago
 style pizza, new york style pizza, current order, and store orders). The initial fragment shown
 is the Chicago style pizza
 @author Siddh Parmar, Yash Patel
 */
public class MainActivity extends AppCompatActivity {
    BottomNavigationView navView;
    public static ChicagoStyle chicagoStyle = new ChicagoStyle();
    public static NewYorkStyle newYorkStyle = new NewYorkStyle();
    public static CurrentOrder currentOrder = new CurrentOrder();
    public static StoreOrders storeOrders = new StoreOrders();
    public static int currentOrderNumber = 1;
    public static Order order = new Order();
    public static StoreOrder allOrders = new StoreOrder();
    public static ArrayList<String> orderNumbers = new ArrayList<>();

    /**
     * Creates the view that will first be shown when the app is launched, as well as setting up
     * the bottom navigation bar that will help the user navigate among different fragments
     * @param savedInstanceState, a Bundle that allows activity to restore itself from previous data
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navView = findViewById(R.id.nav_view);

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

}