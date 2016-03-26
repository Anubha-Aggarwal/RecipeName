package com.example.android.recipeapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class IngradientsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingradients);
        if(savedInstanceState==null)
        {
            getSupportFragmentManager().beginTransaction().
                    add(R.id.recipeIngradContainer, new IngradientsFragment()).commit();
        }
    }
}
