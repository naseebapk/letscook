package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminHome extends AppCompatActivity implements View.OnClickListener {
    Button ingredient,recipe,irecipe,user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        ingredient=(Button)findViewById(R.id.ingredient);
        recipe=(Button)findViewById(R.id.recipe);
        irecipe=(Button)findViewById(R.id.irecipe);
        user=(Button)findViewById(R.id.user);
        user.setOnClickListener(this);
        ingredient.setOnClickListener(this);
        recipe.setOnClickListener(this);
        irecipe.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==user)
        {
            Intent intent = new Intent(AdminHome.this, UserList.class);
            startActivity(intent);
        }
        if(v==ingredient)
        {
            Intent intent = new Intent(AdminHome.this, UserList.class);
            startActivity(intent);
        }
    }
}
