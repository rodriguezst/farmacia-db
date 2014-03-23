package com.rodriguezst.farmacia;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.app.Activity;
import android.content.Intent;

public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		View mTextViewSearch = findViewById(R.id.search);
		
		mTextViewSearch.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LoginActivity.this, MainActivity.class);
				
				startActivity(intent);
				
			}
		});
		
		View mMapButton = findViewById(R.id.map);
		
		mMapButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://maps.google.com/maps?q=farmacia"));
				startActivity(intent);
				
			}
		});
		
		ImageView mMailButton = (ImageView) findViewById(R.id.mail);
		
		mMailButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("mailto:infarmon@gmail.com"));
				startActivity(intent);
				
			}
		});
		
		ImageView mTwitterButton = (ImageView) findViewById(R.id.twitter);
		
		mTwitterButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.twitter.com/infarmon"));
				startActivity(intent);
				
			}
		});
		
		
		ImageView mFbButton = (ImageView) findViewById(R.id.facebook);
		
		mFbButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.facebook.com/infarmon"));
				startActivity(intent);
				
			}
		});
		
	}

}
