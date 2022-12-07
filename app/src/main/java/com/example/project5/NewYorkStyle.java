package com.example.project5;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.text.DecimalFormat;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewYorkStyle#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewYorkStyle extends Fragment implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {
    private Spinner newYorkFlavorSpinner;
    private Spinner newYorkSizeSpinner;
    private TextView newYorkCrustType;
    private ImageView newYorkImageView;
    private ListView toppings;
    private EditText priceEditText;
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
    private PizzaFactory pizzaFactory = new NYPizza();
    private Pizza pizza;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_new_york_style, container, false);
        newYorkFlavorSpinner = view.findViewById(R.id.newYorkFlavorSpinner);
        newYorkSizeSpinner = view.findViewById(R.id.newYorkSizeSpinner);
        flavorAdapter = new ArrayAdapter<String>(this.getActivity(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, flavors);
        sizeAdapter = new ArrayAdapter<String>(this.getActivity(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, sizes);
        newYorkFlavorSpinner.setAdapter(flavorAdapter);
        newYorkSizeSpinner.setAdapter(sizeAdapter);
        newYorkFlavorSpinner.setOnItemSelectedListener(this);
        newYorkSizeSpinner.setOnItemSelectedListener(this);
        newYorkCrustType = view.findViewById(R.id.newYorkCrustType);
        newYorkImageView = view.findViewById(R.id.newYorkImageView);
        setImage("Deluxe");
        priceEditText = view.findViewById(R.id.newYorkPizzaPrice);
        toppings = view.findViewById(R.id.newYorkToppingList);
        toppings.setOnItemClickListener(this);
        toppingAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_multiple_choice,toppingList){
            public boolean isEnabled(int position)
            {
                if(newYorkFlavorSpinner.getSelectedItem().toString().equals("Build Your Own")){
                    return true;
                }
                return false;
            }
        };
        toppings.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        toppings.setAdapter(toppingAdapter);


        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//        switch(adapterView.getId()){
//            case R.id.newYorkFlavorSpinner:
        String selectedFlavor = newYorkFlavorSpinner.getSelectedItem().toString();
        changeView(selectedFlavor);
        //       break;
        //    }
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
            newYorkCrustType.setText(Crust.PAN.crust());
        }
        else if(flavor.equals("Deluxe")){
            newYorkCrustType.setText(Crust.DEEPDISH.crust());
        }
        else if(flavor.equals("Meatzza")){
            newYorkCrustType.setText(Crust.STUFFED.crust());
        }
    }

    private void setImage(String flavor)  {
        if(flavor.equalsIgnoreCase("Build Your Own")){
            newYorkImageView.setImageResource(R.drawable.build_your_own_ny);

        }
        else if(flavor.equalsIgnoreCase("Meatzza")){
            newYorkImageView.setImageResource(R.drawable.meatzza_ny);
        }
        else if(flavor.equalsIgnoreCase("BBQ Chicken")){
            newYorkImageView.setImageResource(R.drawable.bbq_chicken_ny);
        }
        else{
            newYorkImageView.setImageResource(R.drawable.deluxe_ny);
        }
        //pizzaFlavorImageView.setImage(image);
    }

    private void changePrice(){
        DecimalFormat format = new DecimalFormat("#.##");
        priceEditText.setText(format.format(pizza.price()));
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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    public void defaultView() throws FileNotFoundException {
//        pizzaFlavorComboBox.setValue("Build Your Own");
//        smallButton.setSelected(true);
//        pizza = pizzaFactory.createBuildYourOwn();
//        crustTextField.setText(pizza.getCrust().crust());
//        pizzaPriceTextField.setText(Double.toString(pizza.price()));
//        selectedToppingsListView.setItems(null);
//        setImage("Build Your Own");
//        setToppings();
    }

    public void changeView(String flavor){
        setImage(flavor);
        currentCrust(flavor);
        setToppings(flavor);
    }
}