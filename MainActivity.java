package com.example.addingredients;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;

import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener{

//    EditText imeage_id;
    EditText Name;
    Button ADD;
     SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        imeage_id = (EditText) findViewById(R.id.editText);
        Name = (EditText) findViewById(R.id.editText2);
        ADD = (Button) findViewById(R.id.button);


        ADD.setOnClickListener(this);

        db = openOrCreateDatabase("Recipe", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS addingredients(Name VARCHAR);");
    }

    @Override
    public void onClick(android.view.View v) {
        if(Name.getText().toString().trim().length()==0)
            {
                Toast.makeText(MainActivity.this,"please enter values",Toast.LENGTH_SHORT).show();

            }
            db.execSQL("INSERT INTO addingredients VALUES('"+Name.getText()+"');");
            Toast.makeText(MainActivity.this,"Success",Toast.LENGTH_SHORT).show();



    }


//    public onClick(View view)
//    {
//        if(view==ADD)
//        {
//            if(imeage_id.getText().toString().trim().length()==0||
//                    Name.getText().toString().trim().length()==0||)
//            {
//                Toast.makeText(MainActivity.this,"please enter values",Toast.LENGTH_SHORT).show();
//
//            }
//            db.execSQL("INSERT INTO addingredients VALUES('"+imeage_id.getText()+"','"+Name.getText()+"');");
//            Toast.makeText(MainActivity.this,"Success",Toast.LENGTH_SHORT).show();
//
//
//        }
//
//    }
}
