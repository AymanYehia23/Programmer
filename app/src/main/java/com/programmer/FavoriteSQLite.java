package com.programmer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import static com.programmer.Helper.*;

public class FavoriteSQLite extends SQLiteOpenHelper {

    public FavoriteSQLite(Context context) {
        super(context, DATABASE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_WEBSITES_TABLE);
        db.execSQL(CREATE_TUTORIALS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_WEBSITES_TABLE);
        db.execSQL(DROP_TUTORIALS_TABLE);
        onCreate(db);
    }

    public long insert(String table, String column, String position){
        ContentValues contentValues = new ContentValues();
        contentValues.put(column, position);
        return getWritableDatabase().insert(table, null, contentValues);
    }

    public void delete(String table, String column, String position){
        getWritableDatabase().delete(table, column + " = ?", new String[]{position});
    }

    public ArrayList<String> getData(String table, String column){

        String[] projection = {column};

        Cursor cursor = getReadableDatabase().query(table, projection,
                null, null, null, null, null);

        ArrayList <String> positions = new ArrayList <>();
        while(cursor.moveToNext()) {
            String position = cursor.getString(0);
            positions.add(position);
        }
        cursor.close();
        return positions;
    }

}