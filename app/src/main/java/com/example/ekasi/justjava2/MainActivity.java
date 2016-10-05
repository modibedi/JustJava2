package com.example.ekasi.justjava2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {

    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
/**
    * This method is called when the plus button is clicked.
            */
    public void increment(View view) {
        if (quantity == 100){
            //show an error message as a toast
            Toast.makeText(this, " you cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }
        quantity = quantity + 1;
        display(quantity);
    }

    /*** This method is called when the minus button is clicked.
            */
    public void decrement(View view) {
        if (quantity == 1){
            //show an error message as a toast
            Toast.makeText(this, " you cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }
         quantity = quantity - 1;
        display(quantity);

    }


     /*** This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        // Find the users name
        EditText nameField = (EditText)findViewById(R.id.name_field);
        String name = nameField.getText().toString();


        //figure out if the user wants whipped cream topping
        CheckBox whippedCreamCheckBox =(CheckBox) findViewById(R. id. whipped_cream_checkbox);
       boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        //figure out if the user wants chocolate topping
        CheckBox ChocolateCheckBox =(CheckBox) findViewById(R. id. chocolate_checkbox);
        boolean hasChocolate = ChocolateCheckBox.isChecked();

        //figure out if the user wants froth topping
        CheckBox AlmondVanillaCreamCheckBox =(CheckBox) findViewById(R. id. almond_vanilla_cream_checkbox);
        boolean hasAlmondVanillaCream = AlmondVanillaCreamCheckBox.isChecked();

        int price = calculatePrice(hasWhippedCream, hasChocolate, hasAlmondVanillaCream);
          String priceMessage = createOrderSummary(name,price, hasWhippedCream, hasChocolate, hasAlmondVanillaCream );

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for" + name);
        intent.putExtra(Intent.EXTRA_TEXT,priceMessage );
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }


    }





/**
 * Calculates the price of the order
 * @return total price
 * * @param addWhippedCream is whether or not the user wants whipped cream topping
 * * @param addAlmondVanillaCream is whether or not the user wants almond vanilla cream topping
 * * @param addChocolate is whether or not the user wants chocolate cream topping
 */

    private int calculatePrice(boolean addWhippedCream, boolean addChocolate, boolean addAlmondVanillaCream){
        int basePrice = 5;
        //add R2.00 if the user wants Whipped Cream
        if (addWhippedCream){
            basePrice = basePrice + 2;
        }
        //add R3.00 if the user wants Chocolate
        if (addChocolate){
            basePrice = basePrice + 3;
        }
        //add R8.00 if the user wants Almond Vanilla Cream
        if (addAlmondVanillaCream){
            basePrice = basePrice + 8;
        }


        return quantity * basePrice;
    }

    /**
     * create summary of the order
     * @param price
     * @param name of customer
     * @param addWhippedCream is whether or not the user wants whipped cream topping  @return text summary
     */
    private String createOrderSummary(String name, int price,boolean addWhippedCream, boolean addChocolate,boolean addAlmondVanillaCream){
        String priceMessage = "Name: " + name;
        priceMessage += "\nadd whipped cream?" + addWhippedCream;
        priceMessage += "\nadd Chocolate?" + addChocolate;
        priceMessage += "\nadd Almond Vanilla Cream?" + addAlmondVanillaCream;
        priceMessage = priceMessage + "\nQuantity: " + quantity;
        priceMessage = priceMessage + "\nTotal: R" + price;
        priceMessage=priceMessage + " \nThank you, please come again!!";
        return priceMessage;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
     }


    }


