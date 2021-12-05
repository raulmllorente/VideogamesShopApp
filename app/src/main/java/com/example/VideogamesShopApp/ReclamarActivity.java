package com.example.VideogamesShopApp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ReclamarActivity extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView m_imageView;


    //onCreate del activity de la acción reclamar un producto
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reclamar);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        m_imageView = findViewById(R.id.imageView);

        EditText clientName = (EditText) findViewById(R.id.nameClient);
        EditText clientEmail = (EditText) findViewById(R.id.email);
        EditText reclamacion = (EditText) findViewById(R.id.reclamacion);
        Button nButton = (Button) findViewById(R.id.buttonAnadirImg);

        nButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            m_imageView.setImageBitmap(imageBitmap);
            m_imageView.setVisibility(View.VISIBLE);
        }
    }

    //se crea el menu de opciones para enviar el mail de reclamación desde la toolbar
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.menu_layout_send, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //si se clickea el simbolo del mail en el toolbar se "envia" un mail a la empresa con la reclamacion y se avisa al usuario de que ha sido enviado dicho correo
    public boolean onOptionsItemSelected (MenuItem item){
        switch (item.getItemId()){
            case R.id.send_mail:
                Context context = getApplicationContext();
                int time = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, "Correo electrónico con la incidencia enviado. Espere a la respuesta de nuestro servicio de atención al cliente. Muchas gracias por su espera", time);
                toast.show();
                break;
        }
        return true;
    }
}
