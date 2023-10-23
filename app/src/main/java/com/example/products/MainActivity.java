package com.example.products;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText txtBarcode;
    private EditText txtDescription;
    private EditText txtBrand;
    private EditText txtCost;
    private EditText txtPrice;
    private EditText txtStock;
    private Button btnSave;
    private ListView lvProducts;
    private Product product;
    private ProductDAO productDAO;
    GroceriesDbHelper dbHelper=new GroceriesDbHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtBarcode=findViewById(R.id.txt_barcode);
        txtDescription=findViewById(R.id.txt_description);
        txtBrand=findViewById(R.id.txt_brand);
        txtCost=findViewById(R.id.txt_cost);
        txtPrice=findViewById(R.id.txt_price);
        txtStock=findViewById(R.id.txt_stock);
        btnSave=findViewById(R.id.btn_save);
        lvProducts=findViewById(R.id.lv_products);
        productDAO=new ProductDAO(this);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              product= new Product();
              product.setBarcode(txtBarcode.getText().toString());
              product.setDescription(txtDescription.getText().toString());
              product.setBrand(txtBrand.getText().toString());
              product.setCost(Float.parseFloat(txtCost.getText().toString()));
              product.setPrice(Float.parseFloat(txtPrice.getText().toString()));
              product.setStock(Integer.parseInt(txtStock.getText().toString()));
              if (productDAO.insertProduct(product)==true) {
                  Toast.makeText(MainActivity.this, "Producto almancebado con éxito...", Toast.LENGTH_SHORT).show();
              }else{
                  Toast.makeText(MainActivity.this, "Servidor no disponible", Toast.LENGTH_SHORT).show();
              }
            }
        });
    }
}
