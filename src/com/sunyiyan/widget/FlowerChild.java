package com.sunyiyan.widget;

import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class FlowerChild extends View{

	Pot mPot = Pot.getInstance();
	
	public FlowerChild(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	public FlowerChild(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public FlowerChild(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}


	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);
		
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
	    mPot.OnDrawPot(canvas);
	}


	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return super.onTouchEvent(event);
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//////////////////////////////////////My Flower element
	public static class Pot{
		static RectF pot_bottom = new RectF(100, 600, 200, 650);
		Paint paint = new Paint();
		static Pot pot;
		public static synchronized Pot getInstance(){
			if(pot == null)
			{
				pot = new Pot();
			}
			return pot;
		}
		public void OnDrawPot(Canvas canvas)
		{
			paint.setColor(Color.CYAN);
			canvas.drawOval(pot_bottom, paint);
		}
		
	}
    
	
}
