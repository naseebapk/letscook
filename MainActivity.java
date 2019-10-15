package com.example.ingrediantrecipe;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
    EditText   Image_name, Recipe_name,Quantity, Unit;

    Button Add;
    SQLiteDatabase db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Image_name = (EditText) findViewById(R.id.editText);
        Recipe_name = (EditText) findViewById(R.id.editText2);
        Quantity = (EditText) findViewById(R.id.editText3);
        Unit = (EditText) findViewById(R.id.editText4);
        Add = (Button) findViewById(R.id.button);

        Add.setOnClickListener(this);


        db = openOrCreateDatabase("Addrecipe", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Addrecipe(Recipe_id VARCHAR,Recipe_name VARCHAR,Type VARCHAR,Description VARCHAR);");
    }

    public void onClick(View view) {

        if (view == Add) {

            if (Image_name.getText().toString().trim().length() == 0 ||
                    Recipe_name.getText().toString().trim().length() == 0 ||
                    Quantity.getText().toString().trim().length() == 0 ||
                    Unit.getText().toString().trim().length() == 0) {
                Toast.makeText(MainActivity.this, "Please enter values", Toast.LENGTH_SHORT).show();

            }
            db.execSQL("INSERT INTO Addrecipe VALUES('" + Image_name.getText() + "','" + Recipe_name.getText() +
                    "','" + Quantity.getText() + "','" + Unit.getText()+"');");

            Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
        }
    }

}