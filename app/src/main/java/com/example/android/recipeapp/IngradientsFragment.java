package com.example.android.recipeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by User on 26-03-2016.
 */
public class IngradientsFragment extends Fragment {
    private ArrayAdapter<String> mrecipeAdapter;
    int index;
    @Override
    public void onStart() {
        super.onStart();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mrecipeAdapter=new ArrayAdapter<String>(getActivity(),R.layout.list_ingradients,R.id.recipe_ingrad_textview);
        View view=inflater.inflate(R.layout.fragment_ingradients,container,false);
        ListView recipeIngradListView=(ListView)view.findViewById(R.id.list_view_recipeIngrad);
        recipeIngradListView.setAdapter(mrecipeAdapter);
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            int position = intent.getIntExtra("position",10);
            Log.d("position",position+"");
            index=position;
        }

        ArrayList<String> list= FetchDetailsTask.recIngrad.get(index);
        mrecipeAdapter.clear();
        mrecipeAdapter.addAll(list);
        return view;
    }
}
