package com.example.VideogamesShopApp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GameDataHelper extends SQLiteOpenHelper {
    private static final String DBNAME = "gamesdatabase";
    private static final int DBVERSION = 2;


    public GameDataHelper(Context context) {
        super(context, DBNAME, null, DBVERSION);
    }

    //onCreate de la base de datos SQLiteDatabase
    @Override
    public void onCreate(SQLiteDatabase db) {
        //se crean varias tablas, una para cada tipo de categoría (Novedades, Ofertas, PS4, Xbox y pedido cliente)
        //se añaden varios juegos a dichas tablas, excepto en la de clientes que estará vacía, ya que se rellenará dinamicamente

        db.execSQL("CREATE TABLE GAMESNEW ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "NAME TEXT, "
                + "COMPANY TEXT, "
                + "DESCRIPTION TEXT, "
                + "PRICE TEXT, "
                + "PLATFORM TEXT, "
                + "RELEASEDATE TEXT, "
                + "OFFER TEXT, "
                + "IMAGE_ID INTEGER); ");

        addVideogameNew (db, "HALO INFINITE","MICROSOFT", "La legendaria serie Halo regresa con la campaña más amplia del Jefe Maestro hasta la fecha","77.99", "Xbox", "N/A", "No está en oferta", R.drawable.xbox);
        addVideogameNew (db, "GHOST OF TSUSHIMA","SUCKER PUNCH", "A finales del siglo XIII, el Imperio mongol asola naciones enteras en su conquista del Este. La isla de Tsushima es todo lo que se interpone entre las islas principales de Japón y una invasión mongola masiva.","50.00", "PS4", "17/7/2020", "No está en oferta", R.drawable.ps4);
        addVideogameNew (db, "THE LAST OF US 2","NAUGHTY DOG", "Después de un peligroso viaje por un Estados Unidos postapocalíptico, Ellie y Joel se asientan en Wyoming.\nLa vida en una próspera comunidad les proporciona estabilidad, a pesar de la amenaza de los infectados y los supervivientes desesperados. ","45.78", "PS4", "19/6/2020", "No está en oferta", R.drawable.ps4);
        addVideogameNew (db, "DOOM ETERNAL","BETHESDA", "Los ejércitos del infierno han invadido la Tierra. Ponte en la piel del Slayer en una épica campaña para un jugador y cruza dimensiones para detener la destrucción definitiva de la humanidad.","58.99", "Xbox", "20/3/2020", "No está en oferta", R.drawable.xbox);

        db.execSQL("CREATE TABLE GAMESPS4 ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "NAME TEXT, "
                + "COMPANY TEXT, "
                + "DESCRIPTION TEXT, "
                + "PRICE TEXT, "
                + "PLATFORM TEXT, "
                + "RELEASEDATE TEXT, "
                + "OFFER TEXT, "
                + "IMAGE_ID INTEGER); ");

        addVideogamePS4 (db, "GRAND THEFT AUTO V","ROCKSTAR GAMES", "Cuando un joven estafador callejero, un ladrón de bancos retirado y un psicópata aterrador se ven involucrados con lo peor y más desquiciado del mundo criminal, del gobierno de los EE. UU. y de la industria del espectáculo, tendrán que llevar a cabo una serie de peligrosos golpes para sobrevivir en una ciudad implacable en la que no pueden confiar en nadie. Y mucho menos los unos en los otros.","30.00", "PS4", "17/09/13", "No está en oferta", R.drawable.ps4);
        addVideogamePS4 (db, "GHOST OF TSUSHIMA","SUCKER PUNCH", "A finales del siglo XIII, el Imperio mongol asola naciones enteras en su conquista del Este. La isla de Tsushima es todo lo que se interpone entre las islas principales de Japón y una invasión mongola masiva.","50.00", "PS4", "17/7/2020", "No está en oferta", R.drawable.ps4);
        addVideogamePS4 (db, "THE LAST OF US 2","NAUGHTY DOG", "Después de un peligroso viaje por un Estados Unidos postapocalíptico, Ellie y Joel se asientan en Wyoming.\nLa vida en una próspera comunidad les proporciona estabilidad, a pesar de la amenaza de los infectados y los supervivientes desesperados. ","45.78", "PS4", "19/6/2020", "No está en oferta", R.drawable.ps4);
        addVideogamePS4 (db, "UNCHARTED 4","NAUGHTY DOG", "Únete a Drake, Sam, Elena y Sully en una aventura épica alrededor del mundo por islas tropicales, bulliciosas ciudades y cumbres nevadas en busca de una fortuna perdida","20.99", "PS4", "10/5/2016", "Está en oferta", R.drawable.ps4);

        db.execSQL("CREATE TABLE GAMESXBOX ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "NAME TEXT, "
                + "COMPANY TEXT, "
                + "DESCRIPTION TEXT, "
                + "PRICE TEXT, "
                + "PLATFORM TEXT, "
                + "RELEASEDATE TEXT, "
                + "OFFER TEXT, "
                + "IMAGE_ID INTEGER); ");

        addVideogameXbox (db, "FALLOUT 76","BETHESDA", "Sobrevive al yermo y al resto de moradores, de forma cooperativa o solo en este juego de Rol/Acción. ¡El trabajo en equipo te dará mayores posiblidades de sobrevivir!","45.99", "Xbox", "14/11/2018", "No está en oferta", R.drawable.xbox);
        addVideogameXbox (db, "HALO INFINITE","MICROSOFT", "La legendaria serie Halo regresa con la campaña más amplia del Jefe Maestro hasta la fecha","77.99", "Xbox", "N/A", "No está en oferta", R.drawable.xbox);
        addVideogameXbox (db, "DOOM ETERNAL","BETHESDA", "Los ejércitos del infierno han invadido la Tierra. Ponte en la piel del Slayer en una épica campaña para un jugador y cruza dimensiones para detener la destrucción definitiva de la humanidad.","58.99", "Xbox", "20/3/2020", "No está en oferta", R.drawable.xbox);
        addVideogameXbox (db, "FALLOUT 4","BETHESDA", "Bethesda Game Studios, el galardonado creador de Fallout 3 y Skyrim, te presenta el mundo de Fallout 4, ganador de más de 50 premios al Juego del año y los más altos honores en los premios D.I.C.E. 2016. El título, el más ambicioso hasta la fecha, significa una nueva generación de juegos de mundo abierto. Eres el único sobreviviente del Refugio 111 en un mundo destruido por la guerra nuclear. Solo tú puedes reconstruirlo y decidir su futuro. Bienvenido a casa.","15.59", "Xbox", "10/11/2015", "Está en oferta", R.drawable.xbox);
        addVideogameXbox (db, "RED DEAD REDEMPTION 2","ROCKSTAR GAMES", "América, 1899. Arthur Morgan y la banda de Van der Linde se ven obligados a huir. Con agentes federales y los mejores cazarrecompensas de la nación pisándoles los talones, la banda deberá atracar, robar y luchar, para sobrevivir en su camino por el escabroso territorio del corazón de América. Mientras las divisiones internas aumentan y amenazan con separarlos a todos, Arthur deberá elegir entre sus propios ideales y la lealtad a la banda que lo vio crecer.","30.99", "Xbox", "26/10/2018", "Está en oferta", R.drawable.xbox);

        db.execSQL("CREATE TABLE GAMESOFERTAS ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "NAME TEXT, "
                + "COMPANY TEXT, "
                + "DESCRIPTION TEXT, "
                + "PRICE TEXT, "
                + "PLATFORM TEXT, "
                + "RELEASEDATE TEXT, "
                + "OFFER TEXT, "
                + "IMAGE_ID INTEGER); ");

        addVideogameOfertas (db, "FALLOUT 4","BETHESDA", "Bethesda Game Studios, el galardonado creador de Fallout 3 y Skyrim, te presenta el mundo de Fallout 4, ganador de más de 50 premios al Juego del año y los más altos honores en los premios D.I.C.E. 2016. El título, el más ambicioso hasta la fecha, significa una nueva generación de juegos de mundo abierto. Eres el único sobreviviente del Refugio 111 en un mundo destruido por la guerra nuclear. Solo tú puedes reconstruirlo y decidir su futuro. Bienvenido a casa.","15.59", "Xbox", "10/11/2015", "Está en oferta", R.drawable.xbox);
        addVideogameOfertas (db, "RED DEAD REDEMPTION 2","ROCKSTAR GAMES", "América, 1899. Arthur Morgan y la banda de Van der Linde se ven obligados a huir. Con agentes federales y los mejores cazarrecompensas de la nación pisándoles los talones, la banda deberá atracar, robar y luchar, para sobrevivir en su camino por el escabroso territorio del corazón de América. Mientras las divisiones internas aumentan y amenazan con separarlos a todos, Arthur deberá elegir entre sus propios ideales y la lealtad a la banda que lo vio crecer.","30.99", "Xbox", "26/10/2018", "Está en oferta", R.drawable.xbox);
        addVideogameOfertas (db, "UNCHARTED 4","NAUGHTY DOG", "Únete a Drake, Sam, Elena y Sully en una aventura épica alrededor del mundo por islas tropicales, bulliciosas ciudades y cumbres nevadas en busca de una fortuna perdida","20.99", "PS4", "10/5/2016", "Está en oferta", R.drawable.ps4);

        db.execSQL("CREATE TABLE PEDIDOSCLIENTE ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "NAME TEXT, "
                + "COMPANY TEXT, "
                + "PRICE TEXT); ");
    }


    //funciones "add" para añadir los nuevos elementos a las distintas tablas
    public static void addVideogameNew(SQLiteDatabase db, String name, String company, String description, String price, String platform, String releaseDate, String offer, int imageID)
    {
        ContentValues gamesData = new ContentValues();
        gamesData.put("NAME", name);
        gamesData.put("COMPANY", company);
        gamesData.put("DESCRIPTION", description);
        gamesData.put("PRICE", price);
        gamesData.put("PLATFORM", platform);
        gamesData.put("RELEASEDATE", releaseDate);
        gamesData.put("OFFER", offer);
        gamesData.put("IMAGE_ID", imageID);
        db.insert("GAMESNEW", null, gamesData);
    }

    public static void addVideogamePS4 (SQLiteDatabase db, String name, String company, String description, String price, String platform, String releaseDate, String offer, int imageID)
    {
        ContentValues gamesData = new ContentValues();
        gamesData.put("NAME", name);
        gamesData.put("COMPANY", company);
        gamesData.put("DESCRIPTION", description);
        gamesData.put("PRICE", price);
        gamesData.put("PLATFORM", platform);
        gamesData.put("RELEASEDATE", releaseDate);
        gamesData.put("OFFER", offer);
        gamesData.put("IMAGE_ID", imageID);
        db.insert("GAMESPS4", null, gamesData);
    }

    public static void addVideogameXbox (SQLiteDatabase db, String name, String company, String description, String price, String platform, String releaseDate, String offer, int imageID)
    {
        ContentValues gamesData = new ContentValues();
        gamesData.put("NAME", name);
        gamesData.put("COMPANY", company);
        gamesData.put("DESCRIPTION", description);
        gamesData.put("PRICE", price);
        gamesData.put("PLATFORM", platform);
        gamesData.put("RELEASEDATE", releaseDate);
        gamesData.put("OFFER", offer);
        gamesData.put("IMAGE_ID", imageID);
        db.insert("GAMESXBOX", null, gamesData);
    }
    public static void addVideogameOfertas (SQLiteDatabase db, String name, String company, String description, String price, String platform, String releaseDate, String offer, int imageID)
    {
        ContentValues gamesData = new ContentValues();
        gamesData.put("NAME", name);
        gamesData.put("COMPANY", company);
        gamesData.put("DESCRIPTION", description);
        gamesData.put("PRICE", price);
        gamesData.put("PLATFORM", platform);
        gamesData.put("RELEASEDATE", releaseDate);
        gamesData.put("OFFER", offer);
        gamesData.put("IMAGE_ID", imageID);
        db.insert("GAMESOFERTAS", null, gamesData);
    }

    public static void addPedidosCliente (SQLiteDatabase db, String name, String company, String price)
    {
        ContentValues gamesData = new ContentValues();
        gamesData.put("NAME", name);
        gamesData.put("COMPANY", company);
        gamesData.put("PRICE", price);
        db.insert("PEDIDOSCLIENTE", null, gamesData);
    }

    public static void dropPedidosCliente (SQLiteDatabase db, Integer id)
    {
        db.delete("PEDIDOSCLIENTE", "_id"+"="+ id, null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        if (oldVersion == 1 && newVersion == 2)
        {
            db.execSQL("ALTER TABLE GAMESNEW ADD COLUMN FAVORITE BIT DEFAULT 0");
            db.execSQL("ALTER TABLE GAMESPS4 ADD COLUMN FAVORITE BIT DEFAULT 0");
            db.execSQL("ALTER TABLE GAMESXBOX ADD COLUMN FAVORITE BIT DEFAULT 0");
            db.execSQL("ALTER TABLE GAMESOFERTAS ADD COLUMN FAVORITE BIT DEFAULT 0");
            db.execSQL("ALTER TABLE PEDIDOSCLIENTE ADD COLUMN FAVORITE BIT DEFAULT 0");
        }
    }
}
