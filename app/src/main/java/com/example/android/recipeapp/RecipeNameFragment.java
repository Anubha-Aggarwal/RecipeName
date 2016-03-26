package com.example.android.recipeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by User on 25-03-2016.
 */
public class RecipeNameFragment extends android.support.v4.app.Fragment
{
    private ArrayAdapter<String> mrecipeAdapter;

    @Override
    public void onStart() {
        super.onStart();
        FetchDetailsTask task=new FetchDetailsTask(mrecipeAdapter);
        task.execute();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mrecipeAdapter=new ArrayAdapter<String>(getActivity(),R.layout.list_recipe,R.id.recipe_name_textview);
        View view=inflater.inflate(R.layout.fragment_main,container,false);
        final ListView recipeNameListView=(ListView)view.findViewById(R.id.list_view_recipeName);
        recipeNameListView.setAdapter(mrecipeAdapter);
        recipeNameListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("position e",position+"");
                Intent intent = new Intent(getActivity(), IngradientsActivity.class)
                        .putExtra("position",position);
                startActivity(intent);
            }
        });
        return view;
    }
}
