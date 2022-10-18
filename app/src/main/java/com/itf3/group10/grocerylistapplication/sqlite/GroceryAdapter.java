package com.itf3.group10.grocerylistapplication.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.itf3.group10.grocerylistapplication.R;

public class GroceryAdapter extends RecyclerView.Adapter<GroceryAdapter.GroceryViewHolder> {
    private Context context;
    private Cursor cursor;

    public GroceryAdapter(Context context, Cursor cursor){
        this.context = context;
        this.cursor = cursor;
    }

    public class GroceryViewHolder extends RecyclerView.ViewHolder{
        public TextView tvItemName, tvAmount, tvItemDescription;

        public GroceryViewHolder(View itemView) {
            super(itemView);

            tvItemName = (TextView) itemView.findViewById(R.id.tvNameItem);
            tvAmount = (TextView) itemView.findViewById(R.id.tvAmountItem);
            tvItemDescription = (TextView) itemView.findViewById(R.id.tvDescriptionItem);
        }
    }

    @Override
    public GroceryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = inflater.inflate(R.layout.grocery_item, parent, false);
        return new GroceryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroceryViewHolder holder, int position) {
        if(!this.cursor.moveToPosition(position)){
            return;
        }

        String name = this.cursor.getString(this.cursor.getColumnIndexOrThrow(GroceryContract.GroceryEntry.COLUMN_NAME));
        String description = this.cursor.getString(this.cursor.getColumnIndexOrThrow(GroceryContract.GroceryEntry.COLUMN_DESCRIPTION));
        int amount = this.cursor.getInt(this.cursor.getColumnIndexOrThrow(GroceryContract.GroceryEntry.COLUMN_AMOUNT));
        long id = this.cursor.getLong(this.cursor.getColumnIndexOrThrow(GroceryContract.GroceryEntry._ID));

        holder.tvItemName.setText(name);
        holder.tvItemDescription.setText(description);
        holder.tvAmount.setText(String.valueOf(amount));
        holder.itemView.setTag(id);
    }

    @Override
    public int getItemCount() {
        return this.cursor.getCount();
    }

    public void swapCursor(Cursor newCursor){
        if(this.cursor != null){
            this.cursor.close();
        }

        this.cursor = newCursor;

        if(newCursor != null){
            notifyDataSetChanged();
        }
    }
}
