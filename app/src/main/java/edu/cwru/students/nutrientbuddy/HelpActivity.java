package edu.cwru.students.nutrientbuddy;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.icu.text.StringSearch;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class HelpActivity extends AppCompatActivity{

    private OpenItemsMenuHandler openItemsMenuHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_help);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //////////////// Menu Bar Stuff
        this.openItemsMenuHandler = new OpenItemsMenuHandler(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.action_shoppingList:
                intent = new Intent(HelpActivity.this, ShoppingListActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_barcode:
                intent = new Intent(HelpActivity.this, BarcodeActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_recipe:
                intent = new Intent(HelpActivity.this, RecipeListActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_home:
                intent = new Intent(HelpActivity.this, SearchScreenActivity.class);
                intent.putExtra("Text Entered", false);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
