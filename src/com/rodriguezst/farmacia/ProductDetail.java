package com.rodriguezst.farmacia;

import android.os.Bundle;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
//import android.view.Menu;

public class ProductDetail extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_product_detail);
		
		Intent intent = getIntent();
		int mItemId = intent.getIntExtra(MainActivity.ITEM_UID, 0);
		String mItemName = intent.getStringExtra(MainActivity.ITEM_NAME);
		
		TextView mTextViewName = (TextView) findViewById(R.id.prod_name);
		TextView mTextViewId = (TextView) findViewById(R.id.prod_id);
		mTextViewName.setText("Item Name: " + mItemName);
		mTextViewName.setTextColor(Color.LTGRAY);
		mTextViewId.setText("Item ID: " + Integer.toString(mItemId));
		mTextViewId.setTextColor(Color.LTGRAY);
	}

/*	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.product_detail, menu);
		return true;
	}*/

}
