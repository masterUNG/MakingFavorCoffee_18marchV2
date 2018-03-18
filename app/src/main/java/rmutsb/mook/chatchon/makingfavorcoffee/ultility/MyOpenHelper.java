package rmutsb.mook.chatchon.makingfavorcoffee.ultility;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by masterung on 4/1/2018 AD.
 */

public class MyOpenHelper extends SQLiteOpenHelper{

    private Context context;

    public static final String databaseName = "Coffee.db";
    private static final int databaseVersion = 1;
    private static final String detailTable = "create table orderTABLE (" +
            "id integer primary key, " +
            "idLogin text, " +
            "NameCoffee text, " +
            "TypeCoffee text, " +
            "Espresso text," +
            "CocoaPowder text, " +
            "Milk text, " +
            "FrappePowder text, " +
            "Item text);";


    public MyOpenHelper(Context context) {
        super(context, databaseName, null, databaseVersion);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(detailTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}   // Main Class
