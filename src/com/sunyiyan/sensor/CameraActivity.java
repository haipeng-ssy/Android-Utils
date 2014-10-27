package com.sunyiyan.sensor;

import java.io.File;

import com.sunyiyan.R;
import com.sunyiyan.R.layout;
import com.sunyiyan.R.menu;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class CameraActivity extends Activity {

	private Button btn_take_pic_camera,btn_open_camera,btn_take_camera_shooting;

	private String imgPath = "/sdcard/test/img.jpg";
	private String videoPath = "/sdcard/test/img.mp4";
    private final int SYSTEM_CAPTURE = 1;	
    private final int SYSTEM_VIDEO_CAPTURE = 2;	
    File vFile ;
    private ImageView imageView;
    Uri uri;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camera);
		
		btn_take_pic_camera = (Button) findViewById(R.id.btn_take_pic_camera);
		btn_open_camera = (Button) findViewById(R.id.btn_open_camera);
		btn_take_camera_shooting = (Button) findViewById(R.id.btn_take_camera_shooting);
		
		imageView = (ImageView) findViewById(R.id.img_pic);
		
		/**
		 * 调用 camera app
		 * */
		//仅打开相机
		btn_open_camera.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setAction("android.media.action.STILL_IMAGE_CAMERA");
				startActivity(intent);	
			}
		});
		
		btn_take_pic_camera.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				//调用android本身camera app,获取拍的照片。
				vFile = new File(imgPath);
				uri = Uri.fromFile(vFile);
				// TODO Auto-generated method stub
				 if(!vFile.exists())
				    {
				    	File vDirPath = vFile.getParentFile();
				    	vDirPath.mkdirs();
				    }
				 
	            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
	            startActivityForResult(intent, SYSTEM_CAPTURE);
	            
			}
		});
		//实现摄像
		btn_take_camera_shooting.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				//调用android本身camera app,获取拍的照片。
				vFile = new File(videoPath);
				uri = Uri.fromFile(vFile);
				// TODO Auto-generated method stub
				 if(!vFile.exists())
				    {
				    	File vDirPath = vFile.getParentFile();
				    	vDirPath.mkdirs();
				    }
				 
			    	Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
//			    	intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);//保存到默认路径
			    	intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);//保存到指定路径
			    	startActivityForResult(intent, SYSTEM_VIDEO_CAPTURE);
			}
		});
		
		
	}
	

	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode ==SYSTEM_CAPTURE) {
			imageView.setImageURI(Uri.fromFile(new File(imgPath)));
			Bitmap bitmap = (Bitmap) data.getExtras().get("data");
			
		}else if(requestCode == SYSTEM_VIDEO_CAPTURE)
		{
			Uri videoUri = data.getData();
			Cursor cursor = managedQuery(videoUri, null, null, null, null);
			cursor.moveToFirst();
			int num = cursor.getCount();
			String recordedVideoPath = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA));
			int recordedVideoFileSize = cursor.getInt(cursor.getColumnIndex(MediaStore.Video.Media.SIZE));
			Toast.makeText(this, recordedVideoPath+"size="+recordedVideoFileSize, Toast.LENGTH_LONG).show();
			
		}
//		if (resultCode == RESULT_OK) {
//			imageView.setImageURI(Uri.fromFile(new File(imgPath)));
//		}
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.camera, menu);
		return true;
	}

}
