package com.example.VideogamesShopApp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    CategoriesAdapter m_adapter;
    ViewPager         m_pager;

    //onCreate del activity principal de la aplicación
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoadingDB loadingDBData = new LoadingDB();
        loadingDBData.execute();

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        m_adapter = new CategoriesAdapter(getSupportFragmentManager(), this);
        m_pager   = findViewById(R.id.viewpager);
        m_pager.setAdapter(m_adapter);

        TabLayout tab = (TabLayout) findViewById(R.id.tablayout);
        tab.setupWithViewPager(m_pager);

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                0,
                0
        );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationdrawer_view);
        navigationView.setNavigationItemSelectedListener(this);
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
            progressDialog = ProgressDialog.show(MainActivity.this, "Cargando datos", "Por favor espere un momento...");
        }
    }

    @Override
    public void onBackPressed()
    {
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
    {
        int id = menuItem.getItemId();
        switch (id)
        {
            case R.id.navigation_home :
                Log.d("MENUNAVIGATIONDRAWER", "NOVEDADES");
                m_pager.setCurrentItem(0);
                m_adapter.notifyDataSetChanged();
                break;
            case R.id.navigation_ps4 :
                Log.d("MENUNAVIGATIONDRAWER", "PS4");
                m_pager.setCurrentItem(1);
                m_adapter.notifyDataSetChanged();
                break;
            case R.id.navigation_xbox :
                Log.d("MENUNAVIGATIONDRAWER", "XBOX");
                m_pager.setCurrentItem(2);
                m_adapter.notifyDataSetChanged();
                break;
            case R.id.navigation_ofertas :
                Log.d("MENUNAVIGATIONDRAWER", "OFERTAS");
                m_pager.setCurrentItem(3);
                m_adapter.notifyDataSetChanged();
                break;
            case R.id.navigation_contact :
                Intent intent = new Intent(this, ContactoActivity.class);
                startActivity(intent);
                break;
            case R.id.navigation_help :
                Intent intent2 = new Intent(this, MapaActivity.class);
                startActivity(intent2);
                break;
        }
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
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