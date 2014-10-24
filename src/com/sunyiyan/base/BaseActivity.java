package com.sunyiyan.base;


import android.app.Activity;
import android.os.Bundle;

public abstract class BaseActivity extends Activity {

	public abstract void setMyContentView();
	public abstract void initView();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setMyContentView();
		initView();
	}
	
 
	
	
    
}
