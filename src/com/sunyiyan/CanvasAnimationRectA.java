package com.sunyiyan;

import com.sunyiyan.base.BaseActivity;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class CanvasAnimationRectA extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_canvas_animation_rect);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.canvas_animation_rect, menu);
		return true;
	}

	@Override
	public void setMyContentView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		
	}

}
