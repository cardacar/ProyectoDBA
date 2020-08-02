package com.personal.proyectodba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PedidoOBuscar extends AppCompatActivity {

    private  Button order,orderFind,productsAvailable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_o_buscar);

       order = (Button) findViewById(R.id.btnPedir);
       orderFind = (Button) findViewById(R.id.btnorderFind);
       productsAvailable = (Button) findViewById(R.id.btnProductosDisponibles);

       order.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               startActivity(new Intent(PedidoOBuscar.this,Order.class));
           }
       });

       orderFind.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               startActivity(new Intent(PedidoOBuscar.this,OrderFind.class));
           }
       });

       productsAvailable.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               startActivity(new Intent(PedidoOBuscar.this,ProductsAvailable.class));
           }
       });



    }
}