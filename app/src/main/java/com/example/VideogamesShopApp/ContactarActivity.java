package com.example.VideogamesShopApp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ContactarActivity extends AppCompatActivity {

    //onCreate del activity de la acción contactar con la empresa
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactar);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        EditText clientName = (EditText) findViewById(R.id.nameClient);
        EditText clientEmail = (EditText) findViewById(R.id.email);
        EditText consulta = (EditText) findViewById(R.id.consulta);
        Button nButton = (Button) findViewById(R.id.buttonFinishContact);

        nButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ContactarActivity.this, MainActivity.class);
                startActivity(intent);
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
