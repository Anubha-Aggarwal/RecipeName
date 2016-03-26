package com.example.android.recipeapp;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by User on 25-03-2016.
 */

public class FetchDetailsTask extends AsyncTask <Void,Void,String[]>{
   static String recipeJsonString;
    String[] recipeNameArray;
    ArrayAdapter<String> adp;
   static  ArrayList<ArrayList<String>> recIngrad;
    FetchDetailsTask(ArrayAdapter<String> adp)
    {
        this.adp=adp;
    }
    void getRecipeIngradients(String jsonString) throws JSONException
    {
        recIngrad=new ArrayList<ArrayList<String>>();
        JSONObject jsonObject=new JSONObject(jsonString);
        JSONArray recipeArray= jsonObject.getJSONArray("recipe_data");
        for(int i=0;i<recipeArray.length();i++) {
            JSONObject recipeObject = recipeArray.getJSONObject(i);
            JSONArray ingradient_json_array = recipeObject.getJSONArray("ingredient_data");
            Log.d("forecastIn", ingradient_json_array.length() + "ha");
            ArrayList<String> ingradList=new ArrayList<String>();
            for (int j = 0; j < ingradient_json_array.length(); j++) {
                JSONObject ingredient_data_object = ingradient_json_array.getJSONObject(j);
                Log.d("forecastIn", ingradient_json_array.length() + "ha");
                ingradList.add(j, ingredient_data_object.getString("ingredient_name"));
               // Log.d("forecastIn", recipeIngradientsArray[j]);
            }
            recIngrad.add(ingradList);
        }
    }

    String[] getrecipeNameFromJson(String jsonString) throws JSONException
    {
        JSONObject jsonObject=new JSONObject(jsonString);
        JSONArray recipeArray= jsonObject.getJSONArray("recipe_data");
        recipeNameArray=new String[recipeArray.length()];
        for(int i=0;i<recipeArray.length();i++)
        {
            JSONObject recipeObject=recipeArray.getJSONObject(i);
            String name=recipeObject.getString("name");
            Log.d("forecast",name);
            recipeNameArray[i]=name;
        }
        return recipeNameArray;
    }

    /**
     * Override this method to perform a computation on a background thread. The
     * specified parameters are the parameters passed to {@link #execute}
     * by the caller of this task.
     * <p/>
     * This method can call {@link #publishProgress} to publish updates
     * on the UI thread.
     *
     * @param params The parameters of the task.
     * @return A result, defined by the subclass of this task.
     * @see #onPreExecute()
     * @see #onPostExecute
     * @see #publishProgress
     */
    @Override
    protected String[] doInBackground(Void... params) {

        HttpURLConnection urlConnection=null;
        recipeJsonString=null;
        String siteUrl="http://www.speechify.in/internship/android_task.php";
        BufferedReader reader=null;
        try {
            //request and open connection
            URL url = new URL(siteUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            //read input stream into string
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null)
                return null;
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            reader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }
            if (buffer.length() == 0)
                return null;
            recipeJsonString = buffer.toString();
        }
        catch (IOException e) {
            Log.e( "Error ", e.toString());
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e( "Error closing stream", e.toString());
                }
            }
        }
        try
        {
            recipeNameArray= getrecipeNameFromJson(recipeJsonString);
            getRecipeIngradients(recipeJsonString);
            return  recipeNameArray;
        }catch(Exception e)
        {
            Log.d("Error",e.toString());
        }
        return null ;
    }

    @Override
    protected void onPostExecute(String[] strings) {
        if(strings!=null)
        {
            adp.clear();
            for(int i=0;i<strings.length;i++)
                adp.add(strings[i]);
        }
    }
}
