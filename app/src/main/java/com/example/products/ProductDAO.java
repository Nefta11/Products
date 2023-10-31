package com.example.products;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

//Se programan todos los metodos como guardar, eliminar, editar, etc.
public class ProductDAO {

    private Context context;
    private GroceriesDbHelper dbHelper;
    private SQLiteDatabase db;
    public ProductDAO(Context context){
        this.context=context;
        dbHelper=new GroceriesDbHelper(context);
    }
    public boolean insertProduct(Product product){
        boolean result = false;
        db=dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(GroceriesContract.Product.COLUMN_NAME_BARCODE,product.getBarcode());
        values.put(GroceriesContract.Product.COLUMN_NAME_DESCRIPTION,product.getDescription());
        values.put(GroceriesContract.Product.COLUMN_NAME_BRAND,product.getBrand());
        values.put(GroceriesContract.Product.COLUMN_NAME_COST,product.getCost());
        values.put(GroceriesContract.Product.COLUMN_NAME_PRICE,product.getPrice());
        values.put(GroceriesContract.Product.COLUMN_NAME_STOCK,product.getStock());

        long newRowId=db.insert(GroceriesContract.Product.TABLE_NAME,null,values);
        if(newRowId!=-1)
            result=true;
        return result;
    }

    public ArrayList<String> getAllProducts(){
        ArrayList<String> result= new ArrayList<String>();
        db = dbHelper.getReadableDatabase();
        String[] projection={
            GroceriesContract.Product.COLUMN_NAME_BARCODE

        };
        Cursor cursor =db.query(
          GroceriesContract.Product.TABLE_NAME,
          projection,
          null,
          null,
          null,
          null,
          null
        );
        while (cursor.moveToNext()) {
            result.add(cursor.getString(0));
        }
        return result;
    }



}
