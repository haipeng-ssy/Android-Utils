package com.sunyiyan;

import com.sunyiyan.base.BaseActivity;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoViewPlayerActivity extends BaseActivity {

	private Button default_video_player;
	private VideoView videoView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vedio_view_player);
		default_video_player=(Button) findViewById(R.id.default_video_player);
	    default_video_player.setOnClickListener(onClickListener);    
		Uri uri=Uri.parse("/sdcard/Movies/test.mp4");
		videoView=(VideoView) this.findViewById(R.id.my_video_view_player);
		videoView.setMediaController(new MediaController(this));
		videoView.setVideoURI(uri);
		videoView.start();
		videoView.requestFocus();
		
	    
	}
    OnClickListener onClickListener=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Uri uri=Uri.parse("/sdcard/Movies/test.mp4");
			Intent intent=new Intent(Intent.ACTION_VIEW);
			intent.setDataAndType(uri, "video/mp4");
			startActivity(intent);
		}
	};
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.vedio_view_player, menu);
		return true;
	}

}
