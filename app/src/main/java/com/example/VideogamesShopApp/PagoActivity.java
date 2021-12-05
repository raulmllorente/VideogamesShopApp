package com.example.VideogamesShopApp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PagoActivity extends AppCompatActivity{

    //onCreate del activity de la acción pagar la compra
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pago);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        EditText clientName = (EditText) findViewById(R.id.nameClient);
        EditText clientAddress = (EditText) findViewById(R.id.addressClient);
        EditText clientPhone = (EditText) findViewById(R.id.phone);
        EditText clientEmail = (EditText) findViewById(R.id.email);
        Button nButton = (Button) findViewById(R.id.buttonFinishShop);
        CheckBox visa = (CheckBox) findViewById((R.id.checkVisa));
        CheckBox mastercard = (CheckBox) findViewById((R.id.checkMastercard));

        visa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mastercard.setChecked(false);
            }
        });
        mastercard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                visa.setChecked(false);
            }
        });
        nButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.putExtra(Intent.EXTRA_EMAIL, clientEmail.getText().toString());
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Compra Videojuegos Rulo");
                emailIntent.putExtra(Intent.EXTRA_TEXT, clientAddress.getText().toString() + "\n" + clientPhone.getText().toString());
                try {
                    startActivity(Intent.createChooser(emailIntent, "Enviando email al cliente con los detalles de la compra"));
                    finish();

                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(PagoActivity.this, "Servicio de correo no instalado.", Toast.LENGTH_SHORT).show();
                }

                //Intent intent = new Intent(PagoActivity.this, MainActivity.class);
                //startActivity(intent);
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
