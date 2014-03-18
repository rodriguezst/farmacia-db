package com.rodriguezst.farmacia;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
//import android.view.Menu;

public class ProductDetail extends Activity {
	
	private int mItemId;
	private String mItemName;
	private String mItemInfo1;
	private String mItemInfo2;
	
	private TextView mTextViewName;
	private TextView mTextViewId;
	private TextView mTextViewInfo;
	private TextView mTextViewDivider;
	
	
	private Button button1;
	private Button button2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_product_detail);
		
		Intent intent = getIntent();
		mItemId = intent.getIntExtra(MainActivity.ITEM_UID, 0);
		mItemName = intent.getStringExtra(MainActivity.ITEM_NAME);
		mItemInfo1 = intent.getStringExtra(MainActivity.ITEM_INFO1);
		mItemInfo2 = intent.getStringExtra(MainActivity.ITEM_INFO2);
		
		mTextViewName = (TextView) findViewById(R.id.prod_name);
		mTextViewId = (TextView) findViewById(R.id.prod_id);
		mTextViewInfo = (TextView) findViewById(R.id.prod_info);
		mTextViewDivider = (TextView) findViewById(R.id.divider_text);
		mTextViewName.setText(mItemName);
		mTextViewName.setTextColor(Color.WHITE);
		mTextViewId.setText("Item ID: " + Integer.toString(mItemId));
		mTextViewId.setTextColor(Color.WHITE);
		mTextViewInfo.setText(mItemInfo1);
		mTextViewInfo.setTextColor(Color.WHITE);
		mTextViewDivider.setText(R.string.info1);
		mTextViewDivider.setTextColor(Color.WHITE);
		
		button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);
		
		button2.setSelected(false);
		button1.setSelected(true);
		button1.setTextColor(Color.BLACK);
		button2.setTextColor(Color.WHITE);
		
		button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				button2.setSelected(false);
				button1.setSelected(true);
				button1.setTextColor(Color.BLACK);
				button2.setTextColor(Color.WHITE);
				
				mTextViewInfo.setText(mItemInfo1);
				mTextViewDivider.setText(R.string.info1);
				
			}
		});
		
		button2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				button1.setSelected(false);
				button2.setSelected(true);
				button2.setTextColor(Color.BLACK);
				button1.setTextColor(Color.WHITE);
				
				mTextViewInfo.setText(mItemInfo2);
				mTextViewDivider.setText(R.string.info2);
				
			}
		});
		
	}

/*	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.product_detail, menu);
		return true;
	}*/

}
