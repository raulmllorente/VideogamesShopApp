package com.example.VideogamesShopApp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaActivity extends AppCompatActivity implements OnMapReadyCallback{
    private GoogleMap mMap;
    private LocationManager locationManager;
    double latUsr;
    double longUsr;
    double latShop = 40.4286;
    double longShop =  -3.6909;

    //onCreate del activity que implementará la API de Google Maps y calcular la distancia entre el usuario y la tienda
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapa);
        mapFragment.getMapAsync(this);

        if(ContextCompat.checkSelfPermission(MapaActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(MapaActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MapaActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 1, new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                latUsr = location.getLatitude();
                longUsr = location.getLongitude();
                LatLng userLoc = new LatLng(latUsr, longUsr);
                LatLng tiendaLoc = new LatLng(latShop, longShop);
                ((TextView)findViewById(R.id.textoDistancia)).setText(distance(userLoc,tiendaLoc));
            }
        });
    }

    //función que añadirá al mapa de Google Maps la ubicación de la tienda y hará zoom hacia ella, para más comodidad del usuario
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng tiendaLoc = new LatLng(latShop, longShop);
        mMap.addMarker(new MarkerOptions().position(tiendaLoc).title("Tienda Videojuegos Rulo"));
        float zoomLevel = 16.0f;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tiendaLoc, zoomLevel));
    }

    //función que calculará la distancia entre dos puntos, que serán la ubicación del usuario y la de la tienda
    public static String distance(LatLng start, LatLng end){
        Location location1 = new Location("locationA");
        location1.setLatitude(start.latitude);
        location1.setLongitude(start.longitude);
        Location location2 = new Location("locationB");
        location2.setLatitude(end.latitude);
        location2.setLongitude(end.longitude);
        double distance = location1.distanceTo(location2);

        return "Estás a " + (Math.round((distance / 1000) * 100.0) / 100.0) + " kilómetros de la tienda más cercana";
    }
}