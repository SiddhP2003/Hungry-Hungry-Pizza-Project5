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

import java.io.FileNotFoundException;
import java.text.DecimalFormat;

/**
 * A fragment that manages ordering New York style pizza.
 * Allows the user to add a New York style pizza to the current order.
 * @author Siddh Parmar, Yash Patel
 */
public class NewYorkStyle extends Fragment implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener, View.OnClickListener {
    private Spinner newYorkFlavorSpinner;
    private Spinner newYorkSizeSpinner;
    private TextView newYorkCrustType;
    private ImageView newYorkImageView;
    private ListView toppings;
    private EditText priceEditText;
    private Button newYorkAddButton;
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

    /**
     * Creates the view for newYorkStyle.
     * @param inflater Inflater that will inflate the layout with the newYorkStyle layout.
     * @param container Container that holds the layout.
     * @param savedInstanceState The last saved state of the instance.
     * @return View containing the newYorkStyle layout.
     */
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
        // setImage("Deluxe");
        priceEditText = view.findViewById(R.id.newYorkPizzaPrice);
        newYorkAddButton = view.findViewById(R.id.newYorkAddButton);
        newYorkAddButton.setOnClickListener(this);
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
        changeView("Deluxe", "Small");


        return view;
    }

    /**
     * Listener that performs actions based on the item selected.
     * @param adapterView Holds the adapter on which the item was selected.
     * @param view View on which the item was selected.
     * @param i Position of the selected item.
     * @param l The row id of the item that was clicked.
     */
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch(adapterView.getId()){
            case R.id.newYorkFlavorSpinner:
                String selectedFlavor = newYorkFlavorSpinner.getSelectedItem().toString();
                changeView(selectedFlavor, newYorkSizeSpinner.getSelectedItem().toString());
                CharSequence flavor = selectedFlavor.concat(" selected!");
                Toast.makeText(getContext(), flavor, Toast.LENGTH_SHORT).show();
                break;
            case R.id.newYorkSizeSpinner:
                String selectedSize = newYorkSizeSpinner.getSelectedItem().toString();
                currentSize(selectedSize);
                CharSequence size = selectedSize.concat(" selected!");
                Toast.makeText(getContext(), size, Toast.LENGTH_SHORT).show();
                break;

        }
    }

    /**
     * Performs actions when no item is selected.
     * @param adapterView The adapter of the view on which nothing was selected.
     */
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    /**
     * Specializes the flavor of pizza depending on what was chosen in the combo box
     * @param flavor Current pizza flavor
     */
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

    /**
     * Sets the current crust based on the selected flavor.
     * @param flavor Current pizza flavor
     */
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

    /**
     * Displays the specific image of pizza depending on the flavor chosen
     * @param flavor, the flavor that is chosen for the pizza
     */
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
    }

    /**
     * Changes the price of the pizza depending on the flavor of pizza chosen
     */
    private void changePrice(){
        DecimalFormat format = new DecimalFormat("#.##");
        priceEditText.setText("$"+format.format(pizza.price()));
    }

    /**
     * Sets the toppings for the pizza based on the flavor chosen (build your own will have no
     * selected toppings)
     * @param flavor Current pizza flavor
     */
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

    /**
     * Sets the size of the Pizza object based on the selected size.
     * @param size Current pizza size
     */
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

    /**
     * Returns the Topping that correlates to the String name of the topping
     * @param topping, the name of the topping
     * @return Topping, the topping that correlates to the name provided
     */
    private Topping getTopping(String topping){
        for (Topping t:
                Topping.values()) {
            if(t.topping().equals(topping)){
                return t;
            }
        }
        return null;
    }

    /**
     * Listener that performs actions based on the item clicked.
     * @param adapterView Holds the adapter on which the item was selected.
     * @param view View on which the item was clicked.
     * @param i Position of the selected item.
     * @param l The row id of the item that was clicked.
     */
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
                changePrice();
            }
        }
        else if(!toppings.isItemChecked(i)){
            String selectedTopping = adapterView.getAdapter().getItem(i).toString();
            pizza.remove(getTopping(selectedTopping));
            changePrice();
        }
    }

    /**
     * Sets the view based on the selected flavor and size.
     * @param flavor Current pizza flavor
     * @param size Current pizza size
     */
    public void changeView(String flavor, String size){
        setPizza(flavor);
        currentCrust(flavor);
        currentSize(size);
        setImage(flavor);
        changePrice();
        setToppings(flavor);
    }

    /**
     * Performs actions based on when a view is clicked.
     * @param view View that was clicked.
     */
    @Override
    public void onClick(View view) {
        MainActivity.order.add(pizza);
        AlertDialog.Builder alert = new AlertDialog.Builder(this.getActivity());
        alert.setTitle("Pizza Added To Order");
        AlertDialog dialog = alert.create();
        dialog.setIcon(R.drawable.ic_launcher_foreground);
        dialog.show();
    }
}