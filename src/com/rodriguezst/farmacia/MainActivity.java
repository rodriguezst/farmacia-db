package com.rodriguezst.farmacia;


import java.util.ArrayList;

import org.json.JSONException;

import com.rodriguezst.farmacia.ItemContainer;


import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;

public class MainActivity extends Activity implements SearchView.OnQueryTextListener {
	
	private SearchView mSearchView;
    private ListView mListView;

    public final static String ITEM_UID = "com.rodriguezst.farmacia.ITEM_UID";
    public final static String ITEM_NAME = "com.rodriguezst.farmacia.ITEM_NAME";
    public final static String ITEM_INFO1 = "com.rodriguezst.farmacia.ITEM_INFO1";
    public final static String ITEM_INFO2 = "com.rodriguezst.farmacia.ITEM_INFO2";
    
 
    private Filter mFilter = null;
    private ArrayAdapter<ItemContainer> mArrayAdapter;
    
    private ArrayList<ItemContainer> mDatabase;
    private ArrayList<ItemContainer> mDatabaseBackup;

	private ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mListView = (ListView) findViewById(R.id.list_view);
		mSearchView = (SearchView) findViewById(R.id.search_view);
		
//		SearchView style changes disabled and added background instead
//		int id = mSearchView.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
//		TextView textView = (TextView) mSearchView.findViewById(id);
//		textView.setTextColor(Color.WHITE);
//		textView.setHintTextColor(Color.LTGRAY);
		
		progressDialog = new ProgressDialog(this);
		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressDialog.setIndeterminate(true);
		progressDialog.setMessage(getString(R.string.loading_text));
		progressDialog.show();
		
		AsyncTaskClass task = new AsyncTaskClass();
	    task.execute();
        
	}

	
	private void itemOpen(int position){
		//This should take you to another activity passing the selected item
		if(position<mDatabase.size()){
			ItemContainer item = mDatabase.get(position);
			
			int mUID = item.getUID();
			String mName = item.getName();
			String mInfo1 = item.getInfo1();
			String mInfo2 = item.getInfo2();
			
			InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getWindow().getCurrentFocus().getWindowToken(), 0);
			
			Intent intent = new Intent(MainActivity.this, ProductDetail.class);
			intent.putExtra(ITEM_UID, mUID);
			intent.putExtra(ITEM_NAME, mName);
			intent.putExtra(ITEM_INFO1, mInfo1);
			intent.putExtra(ITEM_INFO2, mInfo2);
			startActivity(intent);
		}else{
			Log.i("Carlos", "Pointer error! Position: " + position + " Array Size: " + mDatabase.size());
		}
	}
	
	public boolean onQueryTextChange(String newText) {
		
        mFilter.filter(newText.toString());
        return true;
    }
 
	public boolean onQueryTextSubmit(String query) {
        
		if(mDatabase.size()>0)
			itemOpen(0);
        return true;
    }
	
	
	private void readDatabase() {
		mDatabase = new ArrayList<ItemContainer>();
		mDatabaseBackup = new ArrayList<ItemContainer>();
		
		JSONRead jsonRead = null;

		try {
			jsonRead = new JSONRead(getApplicationContext());
		} catch (JSONException e) {
			// TODO: handle exception
		}
		
		if(jsonRead!=null){
			mDatabase.addAll(jsonRead.getData());
			//finished reading database
			mDatabaseBackup.addAll(mDatabase);
		}else{
			//error reading database
		}
    }

	/* Removed options menu
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}*/
	
	public class ItemAdapter extends ArrayAdapter<ItemContainer> implements Filterable{
		
		
		public ItemAdapter(Context context, int resource, ArrayList<ItemContainer> items) {
			super(context, resource, items);
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent){
			View mView = convertView;
			
			if(mView == null){
				LayoutInflater inflater = (LayoutInflater) MainActivity.this.getLayoutInflater();
				mView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
			}
			if(position<mDatabase.size()){
				TextView mTextView = (TextView) mView.findViewById(android.R.id.text1);
				ItemContainer item = mDatabase.get(position);
				if(item!=null){
					mTextView.setText(item.getName());
					mTextView.setTextColor(Color.WHITE);
					mTextView.setLines(3);
					mTextView.setTypeface(Typeface.create("sans-serif-light", Typeface.NORMAL));
				}
			}
			
			return mView;
		}
		
		@Override
	    public Filter getFilter() {
	        return new Filter() {
				
				@SuppressWarnings("unchecked")
				@Override
				protected void publishResults(CharSequence constraint, FilterResults results) {
					if (results.count > 0) {
						   mDatabase.clear();
				           mDatabase.addAll((ArrayList<ItemContainer>) results.values);
				           notifyDataSetChanged();
				          } else {
				        	  //TODO: Ask the client the expected behavior
				        	  //if uncommented all items in mDatabase will be shown after no results
				              //notifyDataSetInvalidated();
							  mDatabase.clear();
				        	  notifyDataSetChanged();
				          } 
					
				}
				
				@Override
				protected FilterResults performFiltering(CharSequence constraint) {
					FilterResults filterResults = new FilterResults();
					ArrayList<ItemContainer> results = new ArrayList<ItemContainer>();
					
					if(constraint!=null && mDatabaseBackup!=null){
						for(int i=0; i<mDatabaseBackup.size(); i++){
							if(mDatabaseBackup.get(i).getName().toLowerCase().matches("(.*)" + constraint.toString().toLowerCase() + "(.*)")){
								results.add(mDatabaseBackup.get(i));
							}
						}
						filterResults.values = results;
						filterResults.count = results.size();
					}
					return filterResults;
				}
			};
	    }

	}
	
	private class AsyncTaskClass extends AsyncTask<Void, String, String>{
		
		@Override
		protected String doInBackground(Void... params) {
			readDatabase();
			return null;
		}
		
		@Override
	    protected void onPostExecute(String result) {
			//mListView.setTextFilterEnabled(true);
			mArrayAdapter = new ItemAdapter(MainActivity.this,android.R.layout.simple_list_item_1, mDatabase);
			mFilter = mArrayAdapter.getFilter();
			mListView.setAdapter(mArrayAdapter);
			
			mSearchView.setIconifiedByDefault(false);
	        mSearchView.setOnQueryTextListener(MainActivity.this);
	        //mSearchView.setSubmitButtonEnabled(true); 
			
	        mListView.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
				    // When clicked, open the selected item
				    itemOpen(position);
				}
			});
	        
	        progressDialog.dismiss();
	    }
	
	}

}

