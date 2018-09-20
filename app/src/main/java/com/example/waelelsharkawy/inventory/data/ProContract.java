package com.example.waelelsharkawy.inventory.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * API Contract for the product app.
 */
public final class ProContract {

    private ProContract() {
    }

    public static final String CONTENT_AUTHORITY = "com.example.waelelsharkawy.inventory";

    private static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_PRODUCT = "products";

    public static final class ProductEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_PRODUCT);

        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PRODUCT;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PRODUCT;

        /**
         * Name of database table for products
         */
        public final static String TABLE_NAME = "products";
        /**
         * Unique ID number for the product (only for use in the database table).
         * <p>
         * Type: INTEGER
         */
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_PRO_NAME = "name";
        public final static String COLUMN_PRO_PRICE = "price";
        public final static String COLUMN_PRO_QUANTITY = "quantity";
        public final static String COLUMN_PRO_SUPP_NAME = "supplier_name";
        public final static String COLUMN_PRO_SUPP_PHONE = "supplier_phone";
    }
}