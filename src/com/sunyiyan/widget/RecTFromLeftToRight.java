package com.sunyiyan.widget;

import java.util.Timer;
import java.util.TimerTask;

import com.sunyiyan.R;
import com.sunyiyan.interfaceT.InterFaceActivity;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;

public class RecTFromLeftToRight extends View{

	private Context mContext;
	Paint paint ;
	Timer timer;
	int i=0;
	public RecTFromLeftToRight(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	public RecTFromLeftToRight(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		myAnimation.start();
	}
	public RecTFromLeftToRight(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}


	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
//		
//		paint.setColor(Color.BLUE);
//		paint.setStrokeWidth(1.0f);
		Rect rect = new Rect(0, 0, 100, 100);
//		canvas.drawRect(rect, paint);
		Bitmap src = BitmapFactory.decodeResource(getResources(), R.drawable.a);
		canvas.drawBitmap(createCircleBitmap(src), 0, i, paint);
		canvas.translate(0f, 1f);
	}
	
	MyAnimation myAnimation= new MyAnimation() {
		
		@Override
		public void start() {
			// TODO Auto-generated method stub
			
			TimerTask task = new TimerTask() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					i++;
					invalidate();
				}
			};
			
			timer.schedule(task, 10, 10);
		}
		
		@Override
		public void close() {
			// TODO Auto-generated method stub
			timer.cancel();
		}
	};
	
    public void setMyAnimation(MyAnimation myAnimation)
    {
    	this.myAnimation = myAnimation;
    }
	public interface MyAnimation{
    	void start();
    	void close();
    }	
    
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);
	
    }
	
	public Bitmap createCircleBitmap(Bitmap src){
		   int w = src.getWidth();
		   int h = src.getHeight();
		   Bitmap cacheBitmap = Bitmap.createBitmap(w, h, Config.ARGB_8888);
		   Canvas canvas = new Canvas(cacheBitmap);
		   paint =new Paint(Paint.ANTI_ALIAS_FLAG);
		   paint.setColor(Color.BLUE);
		   canvas.drawOval(new RectF(1,1,200,200), paint);
		   paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		   canvas.drawBitmap(src, 0,0, paint);
		   paint.setXfermode(null);
		   return cacheBitmap;
	   }
	

}
