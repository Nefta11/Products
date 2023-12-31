package com.example.products;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

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
    private ArrayList<String> dataOrigin;
    private ArrayAdapter<String> adapter;
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
        updateList();

        lvProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String barcode=(String) lvProducts.getItemAtPosition(i);
                Intent intent = new Intent(MainActivity.this, product_detail.class);
                intent.putExtra("barcode",barcode);
                startActivityForResult(intent, 1); // Lanzar la actividad con un código de solicitud
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                product = new Product();
                product.setBarcode(txtBarcode.getText().toString());
                product.setDescription(txtDescription.getText().toString());
                product.setBrand(txtBrand.getText().toString());
                product.setCost(Float.parseFloat(txtCost.getText().toString()));
                product.setPrice(Float.parseFloat(txtPrice.getText().toString()));
                product.setStock(Integer.parseInt(txtStock.getText().toString()));

                if (productDAO.insertProduct(product)) {
                    Toast.makeText(MainActivity.this, "Producto almacenado con éxito...", Toast.LENGTH_SHORT).show();

                    // Limpiar los campos después de guardar
                    clearFields();

                    // Actualizar la lista
                    updateList();
                } else {
                    Toast.makeText(MainActivity.this, "Error al almacenar el producto", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    // Manejar el resultado de la actividad product_detail
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            // Actualizar la lista de productos después de la eliminación
            updateList();
        }
    }

    protected void updateList(){
        dataOrigin = productDAO.getAllBarcodes();
        adapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, dataOrigin);
        lvProducts.setAdapter(adapter);
    }

    protected void clearFields(){
        txtBarcode.setText("");
        txtDescription.setText("");
        txtBrand.setText("");
        txtCost.setText("");
        txtPrice.setText("");
        txtStock.setText("");
        txtBarcode.requestFocus();
    }
}
