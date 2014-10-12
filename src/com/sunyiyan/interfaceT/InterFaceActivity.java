package com.sunyiyan.interfaceT;

import com.sunyiyan.R;
import com.sunyiyan.R.layout;
import com.sunyiyan.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class InterFaceActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inter_face);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.inter_face, menu);
		return true;
	}
	
	ChangeListener cListener;
	public interface ChangeListener{
		public void change();
	}
	public void setChangeListener(ChangeListener cListener){
		this.cListener=cListener;
	}

}
