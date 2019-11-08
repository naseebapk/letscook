package com.example.cook4;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class recipe extends SQLiteOpenHelper {
    static String DATABASE_NAME="UserDataBase";

    public static final String TABLE_NAME="Recipee";

    public static final String Table_Column_ID="id";

    public static final String Table_Column_1_Name="name";

    public static final String Table_Column_2_Type="type";
    public static final String Table_Column_3_description="description";



    public recipe(Context context) {

        super(context, DATABASE_NAME, null, 1);

    }
    @Override
    public void onCreate(SQLiteDatabase database) {

        String CREATE_TABLE="CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ("+Table_Column_ID+" INTEGER PRIMARY KEY, "+Table_Column_1_Name+" VARCHAR, "+Table_Column_2_Type+" VARCHAR, "+Table_Column_3_description+" VARCHAR)";
        database.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }
    public Cursor viewData()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        String query="Select * from "+TABLE_NAME;
        Cursor cursor=db.rawQuery(query,null);
        return cursor;
    }
    public Cursor viewDetails(String Name)
    {

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME +
                " WHERE " + Table_Column_1_Name + " = '" + Name + "'";
        Cursor c = db.rawQuery(query, null);
        return c;
    }
}


