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
		String mItemInfo1 = intent.getStringExtra(MainActivity.ITEM_INFO1);
		String mItemInfo2 = intent.getStringExtra(MainActivity.ITEM_INFO2);
		
		TextView mTextViewName = (TextView) findViewById(R.id.prod_name);
		TextView mTextViewId = (TextView) findViewById(R.id.prod_id);
		TextView mTextViewInfo1 = (TextView) findViewById(R.id.prod_info1);
		TextView mTextViewInfo2 = (TextView) findViewById(R.id.prod_info2);
		mTextViewName.setText(mItemName);
		mTextViewName.setTextColor(Color.WHITE);
		mTextViewId.setText("Item ID: " + Integer.toString(mItemId));
		mTextViewId.setTextColor(Color.WHITE);
		mTextViewInfo1.setText(mItemInfo1);
		mTextViewInfo1.setTextColor(Color.WHITE);
		mTextViewInfo2.setText(mItemInfo2);
		mTextViewInfo2.setTextColor(Color.WHITE);
	}

/*	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.product_detail, menu);
		return true;
	}*/

}
