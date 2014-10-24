package com.sunyiyan;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.widget.ImageView;

import com.sunyiyan.R;
import com.sunyiyan.base.BaseActivity;
import com.sunyiyan.utils.ImageHelper;
public class CanvasWithBitmapActivity extends BaseActivity {
    private ImageView canvasbitmaptobitmap;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_canvas_with_bitmap);
		
		canvasbitmaptobitmap=(ImageView)findViewById(R.id.canvasbitmaptobitmap);
		Bitmap src = BitmapFactory.decodeResource(getResources(), R.drawable.a);
		Bitmap watermark = BitmapFactory.decodeResource(getResources(), R.drawable.b);
		//canvasbitmaptobitmap.setImageBitmap(ImageHelper.createBitmap(src, watermark));
		canvasbitmaptobitmap.setImageBitmap(ImageHelper.createCircleBitmap(this,200,5));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.canvas_with_bitmap, menu);
		return true;
	}

}
