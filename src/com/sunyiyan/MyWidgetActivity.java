package com.sunyiyan;

import com.sunyiyan.application.MyApplication;
import com.sunyiyan.base.BaseActivity;
import com.sunyiyan.widget.DrawView;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class MyWidgetActivity extends BaseActivity {

	int i=0;
	LinearLayout ll;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_widget);
		
		ll=(LinearLayout) findViewById(R.id.root);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_widget, menu);
		return true;
	}


	
	

}
