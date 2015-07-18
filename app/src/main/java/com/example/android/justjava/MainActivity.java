package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when order button is clicked.
     */
    public void submitOrder(View view){
        int price = calculatePrice();

        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        CheckBox chocolateChecBox = (CheckBox) findViewById(R.id.chocolate_checkbox);

        boolean hasWhippedCream =  whippedCreamCheckBox.isChecked();
        boolean hasChocolate = chocolateChecBox.isChecked();

        displayMessage(createOrderSummary(price, hasWhippedCream, hasChocolate));

    }

    /**
     * Calculates the price of the order
     *
     * @return total price
     */
    private int calculatePrice(){
        return quantity * 5;
    }

    /**
     * Create summary of the order
     *
     * @param price of the order
     * @param hasChocolate is whether or not the user wants whipped cream topping
     * @param hasWhippedCream is whether or not the user wants whipped cream topping
     * @return text summary
     */
    private String createOrderSummary(int price, boolean hasWhippedCream, boolean hasChocolate){
        String priceMessage = "Name: Kinia Kiniaczysko" +
                "\nQuantity: " + quantity +
                "\nAdd whipped cream? "+ hasWhippedCream +
                "\nAdd chocolate? "+ hasChocolate +
                "\nTotal: $"+ price +
                "\nThank you!";
        return priceMessage;
    }

    public void increment(View view){
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    public void decrement(View view){
        quantity = quantity - 1;
        displayQuantity(quantity);
    }


    private void displayQuantity(int quantityNumber) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + quantityNumber);
    }


    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
