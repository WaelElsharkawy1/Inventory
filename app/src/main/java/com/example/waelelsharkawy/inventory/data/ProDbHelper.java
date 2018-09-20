package com.example.waelelsharkawy.inventory.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Database helper for products app. Manages database creation and version management.
 */
public class ProDbHelper extends SQLiteOpenHelper {

    /** Name of the database file */
    private static final String DATABASE_NAME = "inventory.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Constructs a new instance of {@link ProDbHelper}.
     *
     * @param context of the app
     */
    ProDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is created for the first time.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the pets table
        String SQL_CREATE_PETS_TABLE =  "CREATE TABLE " + ProContract.ProductEntry.TABLE_NAME + " ("
                + ProContract.ProductEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ProContract.ProductEntry.COLUMN_PRO_NAME + " TEXT NOT NULL, "
                + ProContract.ProductEntry.COLUMN_PRO_PRICE + " INTEGER NOT NULL DEFAULT 0,"
                + ProContract.ProductEntry.COLUMN_PRO_QUANTITY + " INTEGER NOT NULL DEFAULT 0,"
                + ProContract.ProductEntry.COLUMN_PRO_SUPP_NAME + " TEXT ,"
                + ProContract.ProductEntry.COLUMN_PRO_SUPP_PHONE + " TEXT); ";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_PETS_TABLE);
    }

    /**
     * This is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
    }
}