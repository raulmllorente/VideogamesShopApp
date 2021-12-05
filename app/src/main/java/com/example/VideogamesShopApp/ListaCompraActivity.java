package com.example.VideogamesShopApp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ListaCompraActivity extends AppCompatActivity {

    //onCreate del activity de la lista de juegos comprados por el usuario
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_compra);

        LoadingDB loadingDBData = new LoadingDB();
        loadingDBData.execute();

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListaCompraFragment fragment = (ListaCompraFragment)getSupportFragmentManager().findFragmentById(R.id.album_list_fragment);

        Button button = (Button) findViewById(R.id.buttonBuy);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ListaCompraActivity.this, PagoActivity.class);
                startActivity(intent);
            }
        });
    }
    //Funcion LoadingDB de tipo AsyncTask para simular la carga de la informacion de la base de datos, tiene un tiempo de 5 segundos
    private class LoadingDB extends AsyncTask<String, String, String> {
        ProgressDialog progressDialog;

        @Override
        protected String doInBackground(String... params) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "";
        }

        @Override
        protected void onPostExecute(String result) {
            progressDialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(ListaCompraActivity.this, "Cargando datos", "Por favor espere un momento...");
        }
    }
}
