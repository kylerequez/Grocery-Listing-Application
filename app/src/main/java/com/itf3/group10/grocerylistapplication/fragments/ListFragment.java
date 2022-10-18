package com.itf3.group10.grocerylistapplication.fragments;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.itf3.group10.grocerylistapplication.R;
import com.itf3.group10.grocerylistapplication.sqlite.GroceryAdapter;
import com.itf3.group10.grocerylistapplication.sqlite.GroceryContract;
import com.itf3.group10.grocerylistapplication.sqlite.GroceryDBHelper;

public class ListFragment extends Fragment{
    private SQLiteDatabase sqlDb;
    private GroceryAdapter groceryAdapter;
    private GroceryDBHelper groceryDbHelper;
    private EditText etvItemName;
    private TextView tvAmount;
    private Button btnIncrease, btnDecrease, btnAdd;
    private RecyclerView rvItems;
    private Spinner spDescription;
    private String itemDescription;
    private Context context;
    private int amount = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        this.context = container.getContext();

        groceryDbHelper = new GroceryDBHelper(this.context);
        sqlDb = groceryDbHelper.getWritableDatabase();
        rvItems = view.findViewById(R.id.rvItems);
        rvItems.setLayoutManager(new LinearLayoutManager(this.context));
        rvItems.addItemDecoration(new DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL));
        groceryAdapter = new GroceryAdapter(this.context, getAllItems());
        rvItems.setAdapter(groceryAdapter);

        spDescription = view.findViewById(R.id.spDescription);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getActivity(), R.array.description, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDescription.setAdapter(adapter);
        spDescription.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                itemDescription = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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

        etvItemName = view.findViewById(R.id.etvItemName);
        tvAmount = view.findViewById(R.id.tvAmount);
        btnIncrease = view.findViewById(R.id.btnIncrease);
        btnDecrease = view.findViewById(R.id.btnDecrease);
        btnAdd = view.findViewById(R.id.btnAdd);

        btnIncrease.setOnClickListener(v -> increaseAmount());

        btnDecrease.setOnClickListener(v -> decreaseAmount());

        btnAdd.setOnClickListener(v -> addItem());

        loadTheme();
        return view;
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
            Toast.makeText(this.context, "The amount must not be less than or equal to 0!", Toast.LENGTH_LONG).show();
        }
    }

    private void addItem(){
        if(this.etvItemName.getText().toString().trim().length() == 0 || this.amount == 0){
            Toast.makeText(this.context, "Invalid item input/amount! Please try again.", Toast.LENGTH_LONG).show();
            return;
        }

        ContentValues cv = new ContentValues();
        cv.put(GroceryContract.GroceryEntry.COLUMN_NAME, this.etvItemName.getText().toString());
        cv.put(GroceryContract.GroceryEntry.COLUMN_AMOUNT, this.amount);
        cv.put(GroceryContract.GroceryEntry.COLUMN_DESCRIPTION, this.itemDescription);
        Log.d("NAME", "Name: " + this.etvItemName.getText().toString());
        Log.d("AMOUNT", "Amount: " + this.amount);
        Log.d("DESCRIPTION", "Description: " + this.itemDescription);

        this.sqlDb.insertOrThrow(GroceryContract.GroceryEntry.TABLE_NAME, null, cv);

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

    public void loadTheme(){
        String theme = PreferenceManager.getDefaultSharedPreferences(this.getContext()).getString("prefTheme", "LIGHT");

        switch(theme){
            case "LIGHT":
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;
            case "DARK":
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
        }
    }
}
