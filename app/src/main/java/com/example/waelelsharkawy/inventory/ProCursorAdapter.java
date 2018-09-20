package com.example.waelelsharkawy.inventory;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.waelelsharkawy.inventory.data.ProContract;
import com.example.waelelsharkawy.inventory.data.ProContract.ProductEntry;

/**
 * {@link ProCursorAdapter} is an adapter for a list or grid view
 * that uses a {@link Cursor} of product data as its data source. This adapter knows
 * how to create list items for each row of product data in the {@link Cursor}.
 */
public class ProCursorAdapter extends CursorAdapter  {
    /**
     * Constructs a new {@link ProCursorAdapter}.
     *
     * @param context The context
     * @param c       The cursor from which to get the data.
     */
    public ProCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }
    /**
     * Makes a new blank list item view. No data is set (or bound) to the views yet.
     *
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already
     *                moved to the correct position.
     * @param parent  The parent to which the new view is attached to
     * @return the newly created list item view.
     */
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // Inflate a list item view using the layout specified in list_item.xml
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    /**
     * This method binds the product data (in the current row pointed to by cursor) to the given
     * list item layout. For example, the name for the current product can be set on the name TextView
     * in the list item layout.
     *
     * @param view    Existing view, returned earlier by newView() method
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already moved to the
     *                correct row.
     */
    @SuppressLint("SetTextI18n")
    @Override
    public void bindView(View view, final Context context, final Cursor cursor) {
        // Find individual views that we want to modify in the list item layout
        TextView nameTextView = view.findViewById(R.id.tv_name);
        TextView priceTextView = view.findViewById(R.id.tv_price);
        final TextView quantityTextView = view.findViewById(R.id.tv_quantity);
        final Button btn_sale=view.findViewById(R.id.btn_sale);
        // Find the columns of product attributes that we're interested in
        int nameColumnIndex = cursor.getColumnIndex(ProContract.ProductEntry.COLUMN_PRO_NAME);
        int priceColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRO_PRICE);
        final int quantityColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRO_QUANTITY);
        int idColmnIndex=cursor.getColumnIndex(ProductEntry._ID);
        // Read the product attributes from the Cursor for the current product
        String proName = cursor.getString(nameColumnIndex);
        final int id=cursor.getInt(idColmnIndex);
        int proPrice = cursor.getInt(priceColumnIndex);
        final int proquantity = cursor.getInt(quantityColumnIndex);

        nameTextView.setText(proName);
        priceTextView.setText("price :"+proPrice+" $");
        quantityTextView.setText("Available on store "+proquantity+" unit");
        btn_sale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (proquantity > 0){
                    update_quqntity(proquantity,context ,id);
                    Toast.makeText(context, "sale done", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "there is no items to sale", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void update_quqntity(int quantity ,Context context ,int id) {
        // Create a ContentValues object where column names are the keys,
        // and product attributes from the editor are the values.
        ContentValues values = new ContentValues();
        values.put(ProductEntry.COLUMN_PRO_QUANTITY, quantity-1);
        Uri currentProUri = ContentUris.withAppendedId(ProContract.ProductEntry.CONTENT_URI,id);
        context.getContentResolver().update(currentProUri, values, null, null);
    }
}
