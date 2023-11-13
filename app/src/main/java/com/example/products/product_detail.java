package com.example.products;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class product_detail extends AppCompatActivity {
    private EditText txtBarcode;
    private EditText txtDescription;
    private EditText txtBrand;
    private EditText txtCost;
    private EditText txtPrice;
    private EditText txtStock;

    private Button btnUpdate;
    private Button btnDelete; // Asumiendo que también implementarás el botón de eliminación
    private Product product;
    private ProductDAO productDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        txtBarcode = findViewById(R.id.txt_barcode);
        txtDescription = findViewById(R.id.txt_description);
        txtBrand = findViewById(R.id.txt_brand);
        txtCost = findViewById(R.id.txt_cost);
        txtPrice = findViewById(R.id.txt_price);
        txtStock = findViewById(R.id.txt_stock);
        btnUpdate = findViewById(R.id.btn_update);
        btnDelete = findViewById(R.id.btn_delete);

        String barcode = getIntent().getExtras().getString("barcode");
        productDAO = new ProductDAO(this);
        product = productDAO.getProductByBarcode(barcode);

        if (product != null) {
            txtBarcode.setText(product.getBarcode());
            txtDescription.setText(product.getDescription());
            txtBrand.setText(product.getBrand());
            txtCost.setText(String.valueOf(product.getCost()));
            txtPrice.setText(String.valueOf(product.getPrice()));
            txtStock.setText(String.valueOf(product.getStock()));
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtén los nuevos valores desde los EditText
                String newDescription = txtDescription.getText().toString();
                String newBrand = txtBrand.getText().toString();
                double newCost = Double.parseDouble(txtCost.getText().toString());
                double newPrice = Double.parseDouble(txtPrice.getText().toString());
                int newStock = Integer.parseInt(txtStock.getText().toString());

                // Crea un objeto ContentValues con los nuevos valores
                ContentValues values = new ContentValues();
                values.put(GroceriesContract.Product.COLUMN_NAME_DESCRIPTION, newDescription);
                values.put(GroceriesContract.Product.COLUMN_NAME_BRAND, newBrand);
                values.put(GroceriesContract.Product.COLUMN_NAME_COST, newCost);
                values.put(GroceriesContract.Product.COLUMN_NAME_PRICE, newPrice);
                values.put(GroceriesContract.Product.COLUMN_NAME_STOCK, newStock);

                // Realiza la actualización en la base de datos
                int rowsAffected = productDAO.updateProduct(product.getBarcode(), values);

                // Muestra un mensaje dependiendo del resultado de la actualización
                if (rowsAffected > 0) {
                    Toast.makeText(product_detail.this, "Producto actualizado correctamente", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(product_detail.this, "Error al actualizar el producto", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Código para el botón de eliminación si también lo implementas
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtén el código de barras del producto actual
                String barcode = txtBarcode.getText().toString();

                // Realiza la eliminación en la base de datos
                boolean isDeleted = productDAO.deleteProduct(barcode);

                // Muestra un mensaje dependiendo del resultado de la eliminación
                if (isDeleted) {
                    Toast.makeText(product_detail.this, "Producto eliminado correctamente", Toast.LENGTH_SHORT).show();

                    // Notificar a la actividad principal que se realizó una eliminación
                    Intent intent = new Intent();
                    intent.putExtra("deletedBarcode", barcode);
                    setResult(RESULT_OK, intent);

                    // Cerrar la actividad actual
                    finish();
                } else {
                    Toast.makeText(product_detail.this, "Error al eliminar el producto", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
