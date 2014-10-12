package com.sunyiyan;

import com.sunyiyan.interfaceT.InterFaceActivity;
import com.sunyiyan.interfaceT.InterFaceActivity.ChangeListener;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class SecondActivity<TextView> extends InterFaceActivity implements ChangeListener{

	private TextView textView;
	InterFaceActivity intefaceActivity;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
	    
	}
	public void initView(){
		textView=(TextView) findViewById(R.id.textView1);
	    setChangeListener(cListener);	
		intefaceActivity=new InterFaceActivity();
		intefaceActivity.setChangeListener(cListener);
		
	}
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.second, menu);
		return true;
	}

	ChangeListener cListener=new ChangeListener() {
		
		@Override
		public void change() {
			// TODO Auto-generated method stub
			
		}
	};
	@Override
	public void change() {
		// TODO Auto-generated method stub
		
	}

}
