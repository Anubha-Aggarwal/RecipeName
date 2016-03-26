package com.example.android.recipeapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
final String TAG="recipe_name_tag";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState==null)
        {
            getSupportFragmentManager().beginTransaction().
                    add(R.id.recipeNameContainer, new RecipeNameFragment(),TAG).commit();
        }
    }
}
