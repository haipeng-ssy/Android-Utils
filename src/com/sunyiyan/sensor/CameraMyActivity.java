package com.sunyiyan.sensor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.sunyiyan.MainActivity;
import com.sunyiyan.R;
import com.sunyiyan.R.layout;
import com.sunyiyan.R.menu;

import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.hardware.Camera.Size;
import android.media.MediaRecorder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;
import android.view.Menu;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CameraMyActivity extends Activity implements Callback {

	private SurfaceView mSurfaceView;
	private Camera mCamera;
    private TextView mTextView;
    private Button   mButton,take_video_btn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camera_my);
		mTextView = (TextView) findViewById(R.id.textView1);
		mButton = (Button) findViewById(R.id.take_picture);
		mSurfaceView = (SurfaceView) findViewById(R.id.camera_preview);
		SurfaceHolder surfaceHolder = mSurfaceView.getHolder();
		surfaceHolder.addCallback(this);

		mButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mCamera.stopPreview();
			    mCamera.takePicture(new ShutterCallback() {
					
					@Override
					public void onShutter() {
						// TODO Auto-generated method stub
						//播放快门声
					}
				}, null, new PictureCallback() {
				

					@Override
					public void onPictureTaken(byte[] data, Camera camera) {
						// TODO Auto-generated method stub
						new SavePicPature().execute(data);
						mCamera.startPreview();
					}
				});	
			}
		});
		
		take_video_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				 MediaRecorder:: start开启录制具体可以参阅如下代码:

					 MediaRecorder mMediaRecorder = new MediaRecorder();

					 // Unlock the camera object before passing it to media recorder.

					 mCamera.unlock();

					 mMediaRecorder.setCamera(mCamera);

					 mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);

					 mMediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);

//					 mMediaRecorder.setProfile(mProfile);

					 mMediaRecorder.setMaxDuration(100000);//ms为单位

					 long dateTaken = System.currentTimeMillis();

					 Date date = new Date(dateTaken);

					 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MMdd-HHmmss");

					 String title = dateFormat.format(date);

					 String filename = title + ".3gp"; // Used when emailing.

					 String cameraDirPath ="/sdcard";

					 String filePath = cameraDirPath + "/" + filename;

					 File cameraDir = new File(cameraDirPath);

					 cameraDir.mkdirs();

					 mMediaRecorder.setOutputFile(filePath);

					 try {

					 mMediaRecorder.prepare();

					 mMediaRecorder.start(); // Recording is now started

					 } catch (RuntimeException e) {

					 Log.e("tag", "Could not start media recorder. ", e);

					 return;

					 } catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

//					 7、上面设置了最大间隔为100s，当100是视频录制结束，录制就会被停止，如果没有设时长和文件大小限制，
//					 那么通常需要调用MediaRecorder:: stop函数主动停止视频的录制，并将Camera对象通过lock函数继续加锁，示例代码如下
//
//					 mMediaRecorder.stop();
//
//					 mMediaRecorder.reset();
//
//					 mMediaRecorder.release();
//
//					 mMediaRecorder = null;
//
//					 if(mCamera != null)
//
//					 mCamera.lock();
	
			}
		});
	}
	
	class SavePicPature extends AsyncTask<byte[], String, String>{

		@Override
		protected String doInBackground(byte[]... params) {
			// TODO Auto-generated method stub
			 String imagenameString=System.currentTimeMillis()+".jpg";
			try {
				FileOutputStream outputStream = new FileOutputStream(Environment.getExternalStorageDirectory()
						+"/"+imagenameString);
				outputStream.write(params[0]);
				outputStream.close();
				
			
						// TODO Auto-generated method stub
					    Log.d("tag", imagenameString);
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null;
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.camera_my, menu);
		return true;
	}


    int cameraId;
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
		int mCameraNumbers = mCamera.getNumberOfCameras();
		CameraInfo cameraInfo = new CameraInfo();
		for (int i = 0; i < mCameraNumbers; i++) {
               mCamera.getCameraInfo(i, cameraInfo);
               if (cameraInfo.facing==CameraInfo.CAMERA_FACING_BACK) {
				cameraId = i;
			}
		}
		try {
			mCamera = Camera.open(cameraId);
		} catch (Exception e) {
			// TODO: handle exception
			return;
		}
		
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
         Camera.Parameters parameters = mCamera.getParameters();
         parameters.setPreviewSize(width, height);
         
        
         List<Size> vSizeList = parameters.getSupportedPictureSizes();
         for(int i=0;i<vSizeList.size();i++)
         {
        	 Size vSize = vSizeList.get(i);
        	 
         }
         if (this.getResources().getConfiguration().orientation!=Configuration.ORIENTATION_LANDSCAPE) {
			parameters.set("orientation", "portrait");
			mCamera.setDisplayOrientation(90);
		}else {
			parameters.set("orientation", "landscape");
			mCamera.setDisplayOrientation(0);
		}
         
         try {
			mCamera.setPreviewDisplay(holder);
		} catch (Exception e) {
			// TODO: handle exception
			mCamera.release();
			mCamera = null;
		}
        mCamera.startPreview();
        
        mCamera.autoFocus(new AutoFocusCallback() {
			
			@Override
			public void onAutoFocus(boolean success, Camera camera) {
				// TODO Auto-generated method stub
			   if (success) {
				 mTextView.setVisibility(View.VISIBLE);
			}	
			}
		});
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
        mCamera.release();
	}

}
