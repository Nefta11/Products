package com.example.products;

import android.provider.BaseColumns;

public final class GroceriesContract {
    private GroceriesContract(){}
    //Un CONTRATO es para toda la base de datos

    public static final class Product implements BaseColumns { //Con ver el IMPLEMENTS se sabe que se va a implementar una interfaz grafica
        public static final String TABLE_NAME = "products"; //Nombre de la tabla
        public static final String COLUMN_NAME_BARCODE = "barcode";  //Se pone una constante de tipo STRING para cada campo de la tabla
        public static final String COLUMN_NAME_DESCRIPTION = "descriptios";
        public static final String COLUMN_NAME_BRAND = "bran";
        public static final String COLUMN_NAME_COST = "cost";
        public static final String COLUMN_NAME_PRICE = "price";
        public static final String COLUMN_NAME_STOCK = "stock";
    }
}
