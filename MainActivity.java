package com.example.cook4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    EditText name,type,des;
    Button b1;
    Boolean EditTextEmptyHolder;
    SQLiteDatabase sqLiteDatabaseObj;
    String SQLiteDataBaseQueryHolder ;
    String NameHolder, TypeHolder,DescriptionHolder;
    Cursor cursor;
    recipe recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=(Button)findViewById(R.id.button2);
        name=(EditText)findViewById(R.id.recipename);
        type=(EditText)findViewById(R.id.type);
        des=(EditText)findViewById(R.id.description);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SQLiteDataBaseBuild();
                SQLiteTableBuild();
                CheckEditTextStatus();
                InsertDataIntoSQLiteDatabase();
            }
        });

    }
    public void SQLiteDataBaseBuild(){

        sqLiteDatabaseObj = openOrCreateDatabase(recipe.DATABASE_NAME, Context.MODE_PRIVATE, null);

    }
    public void SQLiteTableBuild() {

        sqLiteDatabaseObj.execSQL("CREATE TABLE IF NOT EXISTS " + recipe.TABLE_NAME + "(" + recipe.Table_Column_ID + " PRIMARY KEY , " + recipe.Table_Column_1_Name + " VARCHAR, " + recipe.Table_Column_2_Type + " VARCHAR, "+ recipe.Table_Column_3_description + " VARCHAR);");

    }
    public void InsertDataIntoSQLiteDatabase() {
        if ((EditTextEmptyHolder == true)) {

            SQLiteDataBaseQueryHolder = "INSERT INTO " + recipe.TABLE_NAME + " (name,type,description) VALUES('" + name.getText().toString() + "', '" + type.getText().toString() + "','" + des.getText().toString() + "');";
            sqLiteDatabaseObj.execSQL(SQLiteDataBaseQueryHolder);

            // Closing SQLite database object.
            sqLiteDatabaseObj.close();

            // Printing toast message after done inserting.
            Toast.makeText(MainActivity.this, "Added", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, viewre.class);
            startActivity(intent);
        } else {
            if (EditTextEmptyHolder == false) {

                // Printing toast message if any of EditText is empty.
                Toast.makeText(MainActivity.this, "Please Fill All The Required Fields.", Toast.LENGTH_LONG).show();

            }


        }
    }
    public void CheckEditTextStatus(){

        // Getting value from All EditText and storing into String Variables.
        NameHolder = name.getText().toString() ;
        TypeHolder = type.getText().toString();
        DescriptionHolder=des.getText().toString();




        if(TextUtils.isEmpty(NameHolder) || TextUtils.isEmpty(TypeHolder) || TextUtils.isEmpty(DescriptionHolder)){

            EditTextEmptyHolder = false ;

        }
        else {

            EditTextEmptyHolder = true ;
        }
    }


}




