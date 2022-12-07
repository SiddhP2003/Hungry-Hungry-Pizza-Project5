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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChicagoStyle#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChicagoStyle extends Fragment implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {
    private Spinner chicagoFlavorSpinner;
    private Spinner chicagoSizeSpinner;
    private TextView chicagoCrustType;
    private ImageView chicagoImageView;
    private ListView toppings;
    private EditText priceEditText;
    private String[] flavors = {"Deluxe", "BBQ Chicken",
            "Meatzza", "Build Your Own"};
    private String[] sizes = {"Large", "Medium", "Small"};
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

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public ChicagoStyle() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment ChicagoStyle.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static ChicagoStyle newInstance(String param1, String param2) {
//        ChicagoStyle fragment = new ChicagoStyle();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//
////        if (getArguments() != null) {
////            mParam1 = getArguments().getString(ARG_PARAM1);
////           mParam2 = getArguments().getString(ARG_PARAM2);
////        }
//
//
//    }

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
        chicagoCrustType = view.findViewById(R.id.chicagoCrustType);
        chicagoImageView = view.findViewById(R.id.chicagoImageView);
        setImage("Build Your Own");
//        //priceEditText = view.findViewById(R.id.)
//        //toppings = view.findViewById(R.id);
//        toppings.setOnItemClickListener(this);
//        toppingAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_multiple_choice,toppingList);
//        toppings.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
//        toppings.setAdapter(toppingAdapter);


        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch(adapterView.getId()){
            case R.id.chicagoFlavorSpinner:
                String selectedFlavor = flavorSpinner.getSelectedItem().toString();
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
            crustTextView.setText(Crust.PAN.crust());
        }
        else if(flavor.equals("Deluxe")){
            crustTextView.setText(Crust.DEEPDISH.crust());
        }
        else if(flavor.equals("Meatzza")){
            crustTextView.setText(Crust.STUFFED.crust());
        }
    }

    private void setImage(String flavor)  {
        if(flavor.equalsIgnoreCase("Build Your Own")){
            pizzaFlavorImageView.setImageResource(R.drawable.build_your_own_chicago);

        }
        else if(flavor.equalsIgnoreCase("Meatzza")){
            pizzaFlavorImageView.setImageResource(R.drawable.meatzza_chicago);
        }
        else if(flavor.equalsIgnoreCase("BBQ Chicken")){
            pizzaFlavorImageView.setImageResource(R.drawable.bbq_chicken_chicago);
        }
        else{
            pizzaFlavorImageView.setImageResource(R.drawable.deluxe_chicago);
        }
        //pizzaFlavorImageView.setImage(image);
    }

    private void changePrice(){
        DecimalFormat format = new DecimalFormat("#.##");
        priceEditText.setText(format.format(pizza.price()));
    }

//    private void setToppings(String flavor){
//        if(flavor.equals("Build Your Own")){
//            for(int i = 0; i < toppingAdapter.getCount(); i++){
//                if(toppingAdapter.getItem())
//            }
//        }
//        else if(flavor.equals("BBQ Chicken")){
//
//        }
//        else if(flavor.equals("Deluxe")){
//        }
//        else if(flavor.equals("Meatzza")){
//
//        }
//    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}