package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.ArrayList;
public class viewnotification extends AppCompatActivity {
    notificationdb db;
    ListView list;
    ArrayList<String> listItem;
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewnotification);
        db=new notificationdb(this);
        listItem=new ArrayList<>();
        list=findViewById(R.id.list_item);
        viewData();
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
