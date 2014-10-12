package com.sunyiyan;

import com.sunyiyan.base.BaseActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends BaseActivity implements OnClickListener{

	private Button button,my_widget_circle_btn,surface_view_player,video_view_player;
	
    Intent intent=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		button = (Button) findViewById(R.id.button1);
		my_widget_circle_btn=(Button) findViewById(R.id.my_widget_circle);
		surface_view_player =(Button) findViewById(R.id.surface_view_player);
		video_view_player = (Button) findViewById(R.id.video_view_player_btn);
		
		button.setOnClickListener(this);
		my_widget_circle_btn.setOnClickListener(this);
		surface_view_player.setOnClickListener(this);
	    video_view_player.setOnClickListener(this);
	}
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	    switch (v.getId()) {
		case R.id.button1:
			
			break;
		case R.id.my_widget_circle:
	        startYourActivity(MyWidgetActivity.class);		
			break;
			
		case R.id.surface_view_player:
			startYourActivity(SurfaceViewPlayerActivity.class);
			break;
		case R.id.video_view_player_btn:
			startYourActivity(VideoViewPlayerActivity.class);
			break;
		default:
			break;
		}
	}
	//跳转到指定的activity
	public void startYourActivity(Class<?> yourActivity){
    	intent=new Intent(this,yourActivity);
    	startActivity(intent);
    }

}
