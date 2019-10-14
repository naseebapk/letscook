package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog.Builder;
import android.database.Cursor;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;



public class UserList extends AppCompatActivity {
    SQLiteHelper db;
    ListView list;
    ArrayList<String> listItem;
    ArrayAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        db=new SQLiteHelper(this);
        listItem=new ArrayList<>();
        list=findViewById(R.id.userlist);
        viewData();
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               String item=(String)list.getItemAtPosition(position);



              Cursor c= db.viewDetails(item);



                // Toast.makeText(UserList.this,c.getColumnName(1),Toast.LENGTH_SHORT).show();
                  StringBuffer stringBuffer = new StringBuffer();
                  while (c.moveToNext()) {
                      stringBuffer.append("Name: " +c.getString(1) + "\n");
                     stringBuffer.append("Email: "+  c.getString(2) + "\n");

                    stringBuffer.append("Age: "+ c.getString(3) + "\n");
                  }
                  Builder builder=new Builder(UserList.this);
                  builder.setCancelable(true);
                  builder.setPositiveButton("ok",null);
                  builder.setTitle("Details");
                  builder.setMessage(stringBuffer.toString());
                  builder.show();
            }});


    }
    private void viewData()
    {
        Cursor cursor=db.viewData();
        if(cursor.getCount()==0)
        {
            Toast.makeText(this,"no data",Toast.LENGTH_LONG).show();
        }
        else
        {
            while(cursor.moveToNext())
            {
                listItem.add(cursor.getString(1));
            }
            adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listItem);
            list.setAdapter(adapter);
        }
    }
}
