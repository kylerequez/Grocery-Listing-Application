package com.itf3.group10.grocerylistapplication.fragments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.itf3.group10.grocerylistapplication.R;
import com.itf3.group10.grocerylistapplication.sqlite.GroceryAdapter;
import com.itf3.group10.grocerylistapplication.sqlite.GroceryContract;
import com.itf3.group10.grocerylistapplication.sqlite.GroceryDBHelper;

public class TestRecyclerView extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private SQLiteDatabase sqlDb;
    private GroceryAdapter groceryAdapter;
    private GroceryDBHelper groceryDbHelper;
    private EditText etvItemName;
    private TextView tvAmount;
    private Button btnIncrease, btnDecrease, btnAdd;
    private RecyclerView rvItems;
    private Spinner spDescription;
    private String itemDescription;
    private int amount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_recycler_view);

        groceryDbHelper = new GroceryDBHelper(getApplicationContext());
        sqlDb = groceryDbHelper.getWritableDatabase();
        rvItems = (RecyclerView) findViewById(R.id.rvItems);
        rvItems.setLayoutManager(new LinearLayoutManager(this));
        groceryAdapter = new GroceryAdapter(this, getAllItems());
        rvItems.setAdapter(groceryAdapter);

        spDescription = (Spinner) findViewById(R.id.spDescription);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.description, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDescription.setAdapter(adapter);
        spDescription.setOnItemSelectedListener(this);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                removeItem((long) viewHolder.itemView.getTag());
            }
        }).attachToRecyclerView(rvItems);

        etvItemName = (EditText) findViewById(R.id.etvItemName);
        tvAmount = (TextView) findViewById(R.id.tvAmount);
        btnIncrease = (Button) findViewById(R.id.btnIncrease);
        btnDecrease = (Button) findViewById(R.id.btnDecrease);
        btnAdd = (Button) findViewById(R.id.btnAdd);

        btnIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increaseAmount();
            }
        });

        btnDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decreaseAmount();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
            }
        });
    }

    private void increaseAmount(){
        this.amount++;
        tvAmount.setText(String.valueOf(this.amount));
    }

    private void decreaseAmount(){
        if(this.amount > 0) {
            this.amount--;
            tvAmount.setText(String.valueOf(this.amount));
        } else {
            Toast.makeText(getApplicationContext(), "The amount must not be less than or equal to 0!", Toast.LENGTH_LONG).show();
        }
    }

    private void addItem(){
        if(this.etvItemName.getText().toString().trim().length() == 0 || this.amount == 0){
            Toast.makeText(getApplicationContext(), "Invalid item input/amount! Please try again.", Toast.LENGTH_LONG).show();
            return;
        }

        //String name = etvItemName.getText().toString();
        ContentValues cv = new ContentValues();
        cv.put(GroceryContract.GroceryEntry.COLUMN_NAME, this.etvItemName.getText().toString());
        cv.put(GroceryContract.GroceryEntry.COLUMN_AMOUNT, this.amount);
        cv.put(GroceryContract.GroceryEntry.COLUMN_DESCRIPTION, this.itemDescription);

        this.sqlDb.insert(GroceryContract.GroceryEntry.TABLE_NAME, null, cv);

        this.groceryAdapter.swapCursor(getAllItems());
        this.etvItemName.getText().clear();
        this.amount = 0;
        this.tvAmount.setText("0");
    }

    private Cursor getAllItems(){
        return sqlDb.query(
                GroceryContract.GroceryEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                GroceryContract.GroceryEntry.COLUMN_TIMESTAMP + " DESC"
        );
    }

    private void removeItem(long id){
        sqlDb.delete(GroceryContract.GroceryEntry.TABLE_NAME,
                GroceryContract.GroceryEntry._ID + "=" + id,
                null);
        groceryAdapter.swapCursor(getAllItems());
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        this.itemDescription = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}