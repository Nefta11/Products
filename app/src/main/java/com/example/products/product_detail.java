package com.example.products;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class product_detail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        String barcode=getIntent().getExtras().getString("barcode");

    }
}