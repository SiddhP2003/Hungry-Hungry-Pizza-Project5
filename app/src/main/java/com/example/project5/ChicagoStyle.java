package com.example.project5;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChicagoStyle#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChicagoStyle extends Fragment implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener, View.OnClickListener {
    private Spinner chicagoFlavorSpinner;
    private Spinner chicagoSizeSpinner;
    private TextView chicagoCrustType;
    private ImageView chicagoImageView;
    private ListView toppings;
    private EditText priceEditText;
    private Button chicagoAddButton;
    private String[] flavors = {"Deluxe", "BBQ Chicken",
            "Meatzza", "Build Your Own"};
    private String[] sizes = {"Small", "Medium", "Large"};
    private String[] toppingList = {Topping.BEEF.topping(),
                        Topping.CHEDDAR.topping(),Topping.PROVOLONE.topping(),Topping.HAM.topping(),
                                Topping.PEPPERONI.topping(), Topping.MUSHROOM.topping(), Topping.BBQChicken.topping()
                                , Topping.ONION.topping(),Topping.SAUSAGE.topping(),
                                Topping.GREENPEPPER.topping(), Topping.BLACKOLIVES.topping(),
                                Topping.PINEAPPLE.topping(), Topping.JALAPENOPEPPERS.topping()};
    private ArrayAdapter<String> flavorAdapter;
    private ArrayAdapter<String> sizeAdapter;
    private ArrayAdapter<String> toppingAdapter;
    private PizzaFactory pizzaFactory = new ChicagoPizza();
    private Pizza pizza;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_chicago_style, container, false);
        chicagoFlavorSpinner = view.findViewById(R.id.chicagoFlavorSpinner);
        chicagoSizeSpinner = view.findViewById(R.id.chicagoSizeSpinner);
        flavorAdapter = new ArrayAdapter<String>(this.getActivity(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, flavors);
        sizeAdapter = new ArrayAdapter<String>(this.getActivity(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, sizes);
        chicagoFlavorSpinner.setAdapter(flavorAdapter);
        chicagoSizeSpinner.setAdapter(sizeAdapter);
        chicagoFlavorSpinner.setOnItemSelectedListener(this);
        chicagoSizeSpinner.setOnItemSelectedListener(this);
        chicagoCrustType = view.findViewById(R.id.chicagoCrustType);
        chicagoImageView = view.findViewById(R.id.chicagoImageView);
       // setImage("Deluxe");
        priceEditText = view.findViewById(R.id.chicagoPizzaPrice);
        chicagoAddButton = view.findViewById(R.id.chicagoAddButton);
        chicagoAddButton.setOnClickListener(this);
        toppings = view.findViewById(R.id.chicagoToppingList);
        toppings.setOnItemClickListener(this);
        toppingAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_multiple_choice,toppingList){
            public boolean isEnabled(int position)
            {
                if(chicagoFlavorSpinner.getSelectedItem().toString().equals("Build Your Own")){
                    return true;
                }
                return false;
            }
        };
        toppings.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        toppings.setAdapter(toppingAdapter);
        changeView("Deluxe", "Small");


        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch(adapterView.getId()){
            case R.id.chicagoFlavorSpinner:
                String selectedFlavor = chicagoFlavorSpinner.getSelectedItem().toString();
                changeView(selectedFlavor, chicagoSizeSpinner.getSelectedItem().toString());
                CharSequence flavor = selectedFlavor.concat(" selected!");
                Toast.makeText(getContext(), flavor, Toast.LENGTH_SHORT).show();
                break;
            case R.id.chicagoSizeSpinner:
                String selectedSize = chicagoSizeSpinner.getSelectedItem().toString();
                currentSize(selectedSize);
                CharSequence size = selectedSize.concat(" selected!");
                Toast.makeText(getContext(), size, Toast.LENGTH_SHORT).show();
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void setPizza(String flavor){
        if(flavor.equals("Build Your Own")){
            pizza = pizzaFactory.createBuildYourOwn();
        }
        else if(flavor.equals("BBQ Chicken")){
            pizza = pizzaFactory.createBBQChicken();
        }
        else if(flavor.equals("Deluxe")){
            pizza = pizzaFactory.createDeluxe();
        }
        else if(flavor.equals("Meatzza")){
            pizza = pizzaFactory.createMeatzza();
        }
    }

    private void currentCrust(String flavor){
        if(flavor.equals("Build Your Own") ||flavor.equals("BBQ Chicken")){
            chicagoCrustType.setText(Crust.PAN.crust());
        }
        else if(flavor.equals("Deluxe")){
            chicagoCrustType.setText(Crust.DEEPDISH.crust());
        }
        else if(flavor.equals("Meatzza")){
            chicagoCrustType.setText(Crust.STUFFED.crust());
        }
    }

    private void setImage(String flavor)  {
        if(flavor.equalsIgnoreCase("Build Your Own")){
            chicagoImageView.setImageResource(R.drawable.build_your_own_chicago);

        }
        else if(flavor.equalsIgnoreCase("Meatzza")){
            chicagoImageView.setImageResource(R.drawable.meatzza_chicago);
        }
        else if(flavor.equalsIgnoreCase("BBQ Chicken")){
            chicagoImageView.setImageResource(R.drawable.bbq_chicken_chicago);
        }
        else{
            chicagoImageView.setImageResource(R.drawable.deluxe_chicago);
        }
    }

    private void changePrice(){
        DecimalFormat format = new DecimalFormat("#.##");
        priceEditText.setText("$"+format.format(pizza.price()));
    }

    private void setToppings(String flavor){
        for(int i = 0; i < toppingAdapter.getCount(); i++){
            toppings.setItemChecked(i,false);
        }
        if(flavor.equals("BBQ Chicken")){
            toppings.setItemChecked(1,true);
            toppings.setItemChecked(2,true);
            toppings.setItemChecked(6,true);
            toppings.setItemChecked(9,true);
        }
        else if(flavor.equals("Deluxe")){
            toppings.setItemChecked(4,true);
            toppings.setItemChecked(5,true);
            toppings.setItemChecked(7,true);
            toppings.setItemChecked(8,true);
            toppings.setItemChecked(9,true);
        }
        else if(flavor.equals("Meatzza")){
            toppings.setItemChecked(4,true);
            toppings.setItemChecked(0,true);
            toppings.setItemChecked(3,true);
            toppings.setItemChecked(8,true);
        }
    }

    private void currentSize(String size){
        if(size.equals("Small")){
            pizza.setSize(Size.SMALL);
            changePrice();
        }
        else if(size.equals("Medium")){
            pizza.setSize(Size.MEDIUM);
            changePrice();
        }
        else if(size.equals("Large")){
            pizza.setSize(Size.LARGE);
            changePrice();
        }
    }

    private Topping getTopping(String topping){
        for (Topping t:
                Topping.values()) {
            if(t.topping().equals(topping)){
                return t;
            }
        }
        return null;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            if(toppings.isItemChecked(i)){
                String selectedTopping = adapterView.getAdapter().getItem(i).toString();
                if(!pizza.add(getTopping(selectedTopping))){
                    AlertDialog.Builder alert = new AlertDialog.Builder(this.getActivity());
                    alert.setTitle("The maximum number of toppings have already been added.");
                    alert.setMessage("Remove a topping to add another.");
                    AlertDialog dialog = alert.create();
                    dialog.show();
                    toppings.setItemChecked(i,false);
                 }
                else{
                    CharSequence addedTopping = selectedTopping.concat(" Added!");
                    Toast.makeText(getContext(), addedTopping, Toast.LENGTH_SHORT).show();
                    changePrice();
                }
            }
            else if(!toppings.isItemChecked(i)){
                String selectedTopping = adapterView.getAdapter().getItem(i).toString();
                pizza.remove(getTopping(selectedTopping));
                CharSequence removedTopping = selectedTopping.concat(" Removed!");
                Toast.makeText(getContext(), removedTopping, Toast.LENGTH_SHORT).show();
                changePrice();
            }
    }


    public void changeView(String flavor, String size){
        setPizza(flavor);
        currentCrust(flavor);
        currentSize(size);
        setImage(flavor);
        changePrice();
        setToppings(flavor);
    }

    @Override
    public void onClick(View view) {
        MainActivity.order.add(pizza);
        AlertDialog.Builder alert = new AlertDialog.Builder(this.getActivity());
        alert.setTitle("Pizza Added To Order");
        AlertDialog dialog = alert.create();
        dialog.show();
    }
}