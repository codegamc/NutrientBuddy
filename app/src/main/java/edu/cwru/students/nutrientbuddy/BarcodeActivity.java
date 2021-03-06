package edu.cwru.students.nutrientbuddy;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class BarcodeActivity extends AppCompatActivity{

    private Button scanBtn;
    private TextView formatTxt, contentTxt;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode);

        scanBtn = (Button)findViewById(R.id.scan_button);
        formatTxt = (TextView)findViewById(R.id.scan_format);
        contentTxt = (TextView)findViewById(R.id.scan_content);

        scanBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator scanIntegrator = new IntentIntegrator(BarcodeActivity.this);
                scanIntegrator.initiateScan();
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);

        if (scanningResult != null) {
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();
            formatTxt.setText("FORMAT: " + scanFormat);
            contentTxt.setText("CONTENT: " + scanContent);
        }else{
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private class FoodSearchAgent extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... strings) {
            Nutritionix nutritionix = new Nutritionix(75);
            nutritionix.loadFoodSearch(strings[0]);
            ArrayList<Food> foods = nutritionix.returnFoodListAsFood();

            Intent intent = new Intent(BarcodeActivity.this, SearchScreenActivity.class);
            intent.putExtra("Search Entered", foods.get(0).get(Food.name));
            intent.putExtra("Text Entered", true);
            startActivity(intent);

            return null;
        }

        protected void onPostExecute(Void v) {

        }
    }
}
