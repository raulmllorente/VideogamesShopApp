package com.example.VideogamesShopApp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.fragment.app.ListFragment;

public class ListaCompraFragment extends ListFragment {

    public ListaCompraFragment() {
        // Constructor vacío
    }

    //Se crea la vista del fragment con los elementos de la cesta del usuario
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        SQLiteOpenHelper gameDbHelper = new GameDataHelper(getContext()) ;
        SQLiteDatabase db = gameDbHelper.getReadableDatabase();
        Cursor cursor = db.query("PEDIDOSCLIENTE",
                new String[] {"_id", "NAME"},
                null,
                null,
                null, null, null);
        SimpleCursorAdapter listAdapter = new SimpleCursorAdapter(
                getContext(),
                android.R.layout.simple_list_item_1,
                cursor,
                new String[]{"NAME"},
                new int[] {android.R.id.text1},
                0);
        setListAdapter(listAdapter);
        return inflater.inflate(R.layout.fragment_lista_compra, container, false);
    }

    //función que permite eliminar un juego de la cesta de compra según el juego seleccionado
    public void onListItemClick(ListView l, View v, int position, long id)
    {
        SQLiteOpenHelper gameDbHelper = new GameDataHelper(getContext()) ;
        try
        {
            SQLiteDatabase db = gameDbHelper.getReadableDatabase();
            Cursor cursor = db.query("PEDIDOSCLIENTE",
                    new String[] {"_id"},
                    null,
                    null,
                    null, null, null);
            cursor.move(position+1);
            GameDataHelper.dropPedidosCliente(db, cursor.getInt(0));
        }
        catch (Exception e) {
        }
    }
}
