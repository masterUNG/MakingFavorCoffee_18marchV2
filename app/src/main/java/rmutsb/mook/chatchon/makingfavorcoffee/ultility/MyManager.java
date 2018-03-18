package rmutsb.mook.chatchon.makingfavorcoffee.ultility;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by masterung on 4/1/2018 AD.
 */

public class MyManager {

    private Context context;
    private MyOpenHelper myOpenHelper;
    private SQLiteDatabase sqLiteDatabase;

    public MyManager(Context context) {
        this.context = context;

        myOpenHelper = new MyOpenHelper(context);
        sqLiteDatabase = myOpenHelper.getWritableDatabase();

    }   // Constructor

    public long addValueToSQLite(String idOrderString,
                                 String nameCoffeeString,
                                 String typeCoffeeString,
                                 String espressoString,
                                 String cocoapowderString,
                                 String milkString,
                                 String frappePowderString,
                                 String itemString) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("idLogin", idOrderString);
        contentValues.put("NameCoffee", nameCoffeeString);
        contentValues.put("TypeCoffee", typeCoffeeString);
        contentValues.put("Espresso", espressoString);
        contentValues.put("Cocoapowder", cocoapowderString);
        contentValues.put("Milk", milkString);
        contentValues.put("FrappePowder", frappePowderString);
        contentValues.put("Item", itemString);


        return sqLiteDatabase.insert("orderTABLE", null, contentValues);
    }


}   // Main Class
