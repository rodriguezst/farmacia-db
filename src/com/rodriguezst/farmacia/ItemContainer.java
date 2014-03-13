package com.rodriguezst.farmacia;


public class ItemContainer {
	
	private String mProdName;
	private int mUID;
	
	public ItemContainer(String product, int uid) {
		// TODO Auto-generated constructor stub
		mProdName = product;
		mUID = uid;
	}

	public String getName(){
		return mProdName;
	}
	public int getUID(){
		return mUID;
	}
}

