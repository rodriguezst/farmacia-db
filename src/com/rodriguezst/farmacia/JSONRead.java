package com.rodriguezst.farmacia;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;
import android.content.res.AssetManager;

import com.rodriguezst.farmacia.ItemContainer;

public class JSONRead {
	
	ArrayList<ItemContainer> result = new ArrayList<ItemContainer>();

	public JSONRead( Context context) throws JSONException {
		AssetManager assetManager = context.getAssets();
		InputStream inputStream = null;
		try {
			String data = null;
			
			inputStream = assetManager.open("database.json");
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
		    StringBuilder stringBuilder = new StringBuilder();

		    String line = null;
		    while ((line = reader.readLine()) != null)
		    {
		        stringBuilder.append(line + "\n");
		    }
		    data = stringBuilder.toString();
		    
		    
		    
		    //JSONObject jsonObj = new JSONObject(data);
		    JSONArray jsonArray = new JSONArray(data);
		    //JSONArray jsonArray = jsonObj.getJSONArray("database");
		    for (int i = 0; i < jsonArray.length(); i++) { 
		    	result.add(new ItemContainer(i,jsonArray.getJSONObject(i).getString("nombre"),jsonArray.getJSONObject(i).getString("info1"),jsonArray.getJSONObject(i).getString("info2")));
		    } 
		    
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public ArrayList<ItemContainer> getData(){
		return result;
	}

}
