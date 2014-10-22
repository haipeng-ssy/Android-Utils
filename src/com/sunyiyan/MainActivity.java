package com.sunyiyan;

import com.sunyiyan.base.BaseActivity;
import com.sunyiyan.sensor.WifiActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends BaseActivity implements OnClickListener{

	private Button button,my_widget_circle_btn,surface_view_player,video_view_player,
	canvas_bitmap_btn,wifi_btn,rect_btn;
	
    Intent intent=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		button = (Button) findViewById(R.id.button1);
		my_widget_circle_btn=(Button) findViewById(R.id.my_widget_circle);
		surface_view_player =(Button) findViewById(R.id.surface_view_player);
		video_view_player = (Button) findViewById(R.id.video_view_player_btn);
		canvas_bitmap_btn = (Button)findViewById(R.id.canvas_bitmap_btn);
		wifi_btn = (Button)findViewById(R.id.wifi_btn);
		rect_btn = (Button)findViewById(R.id.rect_btn);
		
		button.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/DINOT-Regular.otf"));
		my_widget_circle_btn.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf"));
		surface_view_player.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Roboto-Bold.ttf"));
		canvas_bitmap_btn.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light-Italic.ttf"));
		wifi_btn.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Roboto-Medium.ttf"));
		rect_btn.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf"));
		
		button.setOnClickListener(this);
		my_widget_circle_btn.setOnClickListener(this);
		surface_view_player.setOnClickListener(this);
	    video_view_player.setOnClickListener(this);
	    canvas_bitmap_btn.setOnClickListener(this);
	    wifi_btn.setOnClickListener(this);
	    rect_btn.setOnClickListener(this);
	    
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
		case R.id.canvas_bitmap_btn:
			startYourActivity(CanvasWithBitmapActivity.class);
			break;
		case R.id.wifi_btn:
			startYourActivity(WifiActivity.class);
			break;
		case R.id.rect_btn:
			startYourActivity(CanvasAnimationRectA.class);
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
