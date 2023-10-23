package com.example.products;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GroceriesDbHelper extends SQLiteOpenHelper {
    public static final String DATA_BASE_NAME="groceries.db";
    public static final int DATABASE_VERSION=1;
    public static final String SQL_CREATE_PRODUCTS = "CREATE TABLE " +
            GroceriesContract.Product.TABLE_NAME + " (" +
            GroceriesContract.Product.COLUMN_NAME_BARCODE + " TEXT PRIMARY KEY, " + // Será nuestra llave primaria
            GroceriesContract.Product.COLUMN_NAME_DESCRIPTION + " TEXT, " + // Se van definiendo los tipos de datos
            GroceriesContract.Product.COLUMN_NAME_BRAND + " TEXT, " +
            GroceriesContract.Product.COLUMN_NAME_COST + " FLOAT, " +
            GroceriesContract.Product.COLUMN_NAME_PRICE + " FLOAT, " +
            GroceriesContract.Product.COLUMN_NAME_STOCK + " INTEGER" +
            ");";

    public static final String SQL_DELETE_PRODUCTS="DROP TABLE IF EXISTS " + GroceriesContract.Product.TABLE_NAME; //Se invoca en caso de que exista la tabla que la elimine
    public GroceriesDbHelper(Context context){
        super(context, DATA_BASE_NAME, null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_PRODUCTS); //Cuando no exista la tabla se va a crear
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    sqLiteDatabase.execSQL(SQL_DELETE_PRODUCTS); //En caso de que exista la tabla la elimina y la vuelve a crear
    onCreate(sqLiteDatabase);
    }
}
