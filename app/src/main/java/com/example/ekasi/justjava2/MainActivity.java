package com.example.ekasi.justjava2;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

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
        quantity = quantity + 1;
        display(quantity);
    }

    /*** This method is called when the minus button is clicked.
            */
    public void decrement(View view) {
         quantity = quantity - 1;
        display(quantity);

    }


     /*** This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price = calculatePrice();
          String priceMessage = createOrderSummary(price);
        displayMessage(priceMessage);
    }
/**
 * Calculates the price of the order
 * @return total price
 */

    private int calculatePrice(){
        int price = quantity * 5;
        return price ;
    }

    /**
     * create summary of the order
     *
     * @param price
     * @return text summary
     */


    private String createOrderSummary(int price){
        String priceMessage = "Name: Herold Modibedi";
        priceMessage = priceMessage + "Quantity: " + quantity;
        priceMessage = priceMessage + "Total: R" + price;
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

    /**
     * this method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}
