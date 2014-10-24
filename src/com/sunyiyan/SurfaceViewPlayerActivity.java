package com.sunyiyan;

import com.sunyiyan.base.BaseActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SurfaceViewPlayerActivity extends BaseActivity implements SurfaceHolder.Callback {

	MediaPlayer player;
	SurfaceView surface;
	SurfaceHolder surfaceHolder;
	Button play,pause,stop;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_surface_view_player);
	 
		play=(Button) findViewById(R.id.button1);
		pause =(Button) findViewById(R.id.button2);
		stop = (Button) findViewById(R.id.button3);
		surface=(SurfaceView) findViewById(R.id.surface);
		
		surfaceHolder = surface.getHolder();
		surfaceHolder.addCallback(this);
		surfaceHolder.setFixedSize(320, 220);//显示的分辨率,不设置为视频默认
		surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);//Surface类型
		play.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				player.start();
			}
		});
		pause.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				player.pause();
			}
		});
		stop.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				player.stop();
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.surface_view_player, menu);
		return true;
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		//必须在surface创建后才能初始化MediaPlayer,否则不会显示图像
		player = new MediaPlayer();
		player.setAudioStreamType(AudioManager.STREAM_MUSIC);
		player.setDisplay(surfaceHolder);
		////设置显示视频显示在SurfaceView上
        try {
			player.setDataSource("/sdcard/movies/test.mp4");
			player.prepare();
		} catch (Exception e) {
			// TODO: handle exception
		    e.printStackTrace();
		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if(player.isPlaying())
		{
			player.stop();
		}
		player.release();
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
