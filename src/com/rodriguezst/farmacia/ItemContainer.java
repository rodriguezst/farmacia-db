package com.rodriguezst.farmacia;


public class ItemContainer {
	
	private String mProdName;
	private int mUID;
	private String mInfo1;
	private String mInfo2;
	
	public ItemContainer(int uid, String product, String info1, String info2) {
		// TODO Auto-generated constructor stub
		mProdName = product;
		mUID = uid;
		mInfo1 = info1;
		mInfo2 = info2;
		
	}

	public String getName(){
		return mProdName;
	}
	public int getUID(){
		return mUID;
	}
	public String getInfo1(){
		return mInfo1;
	}
	public String getInfo2(){
		return mInfo2;
	}
}

