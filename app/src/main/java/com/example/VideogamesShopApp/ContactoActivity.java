package com.example.VideogamesShopApp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

public class ContactoActivity extends AppCompatActivity {

    //onCreate del activity de la acción contacto con las opciones de reclamacion y contactar
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button nButton = (Button) findViewById(R.id.buttonContinuar);
        CheckBox contactar = (CheckBox) findViewById((R.id.checkContactar));
        CheckBox reclamarPedido = (CheckBox) findViewById((R.id.checkReclamarPedido));

        contactar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reclamarPedido.setChecked(false);
            }
        });
        reclamarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactar.setChecked(false);
            }
        });

        nButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(contactar.isChecked() == true) {
                    Intent intent = new Intent(ContactoActivity.this, ContactarActivity.class);
                    startActivity(intent);
                } else {
                    if(reclamarPedido.isChecked() == true) {
                        Intent intent2 = new Intent(ContactoActivity.this, ReclamarActivity.class);
                        startActivity(intent2);
                    }
                }

            }
        });
    }

    //se crea el menu de opciones para acceder al carrito desde la toolbar
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.menu_layout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //si se clickea el carrito en el toolbar se accede a la lista de la compra del cliente
    public boolean onOptionsItemSelected (MenuItem item){
        switch (item.getItemId()){
            case R.id.shop_action:
                Intent intent = new Intent(this, ListaCompraActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }
}
