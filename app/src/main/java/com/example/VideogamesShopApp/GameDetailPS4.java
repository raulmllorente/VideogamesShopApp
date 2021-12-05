package com.example.VideogamesShopApp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameDetailPS4 extends AppCompatActivity {
    int gameId;

    //onCreate del activity de la informacion de los juegos de tipo "PS4"
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_detail_ps4);

        LoadingDB loadingDBData = new LoadingDB();
        loadingDBData.execute();

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        gameId = Integer.valueOf(getIntent().getStringExtra("GAMEID"));
        SQLiteOpenHelper gameDbHelper = new GameDataHelper(this) ;
        try {
            SQLiteDatabase db = gameDbHelper.getReadableDatabase();
            Cursor cursor = db.query("GAMESPS4",
                    new String[]{"_id", "NAME", "COMPANY", "DESCRIPTION", "PRICE", "PLATFORM", "RELEASEDATE", "OFFER", "IMAGE_ID"},
                    "_id = ?",
                    new String[]{Integer.toString(gameId)},
                    null, null, null);
            cursor.moveToFirst();
            gameId = cursor.getInt(0);
            ((TextView)findViewById(R.id.videogameName)).setText(cursor.getString(1));
            ((TextView)findViewById(R.id.companyName)).setText(cursor.getString(2));
            ((TextView)findViewById(R.id.description)).setText(cursor.getString(3));
            ((TextView)findViewById(R.id.price)).setText(cursor.getString(4));
            ((TextView)findViewById(R.id.platform)).setText(cursor.getString(5));
            ((TextView)findViewById(R.id.releaseDate)).setText(cursor.getString(6));
            ((TextView)findViewById(R.id.offer)).setText(cursor.getString(7));
            ((ImageView)findViewById(R.id.gameImage)).setImageResource(cursor.getInt(8));
            Button button = (Button) findViewById(R.id.buttonAddCart);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    GameDataHelper.addPedidosCliente(db, cursor.getString(1), cursor.getString(2), cursor.getString(4));
                }
            });
        }
        catch (Exception e){
        }

    }

    //Funcion LoadingDB de tipo AsyncTask para simular la carga de la informacion de la base de datos, tiene un tiempo de 2 segundos
    private class LoadingDB extends AsyncTask<String, String, String> {
        ProgressDialog progressDialog;

        @Override
        protected String doInBackground(String... params) {
            try {
                Thread.sleep(2000);
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
            progressDialog = ProgressDialog.show(GameDetailPS4.this, "Cargando datos", "Por favor espere un momento...");
        }
    }

    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.menu_layout, menu);
        return super.onCreateOptionsMenu(menu);
    }

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
