package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


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

        EditText nameField = (EditText) findViewById(R.id.name_field);
        String name = nameField.getText().toString();

        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);

        boolean hasWhippedCream =  whippedCreamCheckBox.isChecked();
        boolean hasChocolate = chocolateCheckBox.isChecked();

        int price = calculatePrice(hasWhippedCream, hasChocolate);
        String message = createOrderSummary(price, hasWhippedCream, hasChocolate, name);


        Intent email= new Intent(Intent.ACTION_SEND);
        email.setData(Uri.parse("mailto:"));
        email.setType("text/plain");
        email.putExtra(email.EXTRA_SUBJECT, getString(R.string.order_summary_email_subject) + name);
        email.putExtra(email.EXTRA_TEXT, message);

        if (email.resolveActivity(getPackageManager()) != null) {
            startActivity(email);
        }

    }

    /**
     * Calculates the price of the order
     *
     * @return total price
     */
    private int calculatePrice(boolean hasWhippedCream, boolean hasChocolate){
        // Price of 1 cup of coffee
        int price = 5;

        // Add $1 if the user wants whipped cream
        if(hasWhippedCream){
            price += 1;
        }

        //Add $2 if the user wants chocolate
        if(hasChocolate) {
            price += 2;
        }

        //Calculate the total order price by multiplying by quantity
        return quantity * price;
    }

    /**
     * Create summary of the order
     *
     * @param price of the order
     * @param hasChocolate is whether or not the user wants whipped cream topping
     * @param hasWhippedCream is whether or not the user wants whipped cream topping
     * @param name is name of person ordering coffee
     * @return text summary
     */
    private String createOrderSummary(int price, boolean hasWhippedCream, boolean hasChocolate, String name){

        String priceMessage =  getString(R.string.order_summary_name) + name;
        priceMessage += "\n" + getString(R.string.order_summary_quantity) + quantity;
        priceMessage += "\n" + getString(R.string.order_summary_whipped_cream) + hasWhippedCream ;
        priceMessage += "\n" + getString(R.string.order_summary_chocolate) + hasChocolate;
        priceMessage += "\n" + getString(R.string.order_summary_price) + price;
        priceMessage += "\n" + getString(R.string.thank_you);

        return priceMessage;
    }

    public void increment(View view){
        if(quantity == 100){
            // Show an error message as a toast
            Toast.makeText(this, "You cannot have more than 100 coffee", Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    public void decrement(View view){
        if(quantity == 1){
            // Show an error message as a toast
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_LONG).show();
            // Exit this method early becouse there's nothing left to do
            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);
    }


    private void displayQuantity(int quantityNumber) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + quantityNumber);
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
