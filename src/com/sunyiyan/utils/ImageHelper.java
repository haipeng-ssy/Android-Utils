package com.sunyiyan.utils;

import com.sunyiyan.R;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;

public class ImageHelper {
       public static Bitmap createBitmap(Bitmap src,Bitmap watermark){
    	   
    	   int w = src.getWidth();
    	   int h = src.getHeight();
    	   int ww = watermark.getWidth();
    	   int hh = watermark.getHeight();
    	   
    	   //create a bitmap like with src;
    	   Bitmap newb = Bitmap.createBitmap(w,h,Config.ARGB_8888);
    	   
    	   Canvas cv = new Canvas(newb);
    	   //draw src into 
    	   cv.drawBitmap(src, 0, 0, null);
    	   //draw watermark into
    	   cv.drawBitmap(watermark, w-ww+5, h-hh+5, null);
    	   //save all clip
    	   cv.save(Canvas.ALL_SAVE_FLAG);
    	   //store
    	   cv.restore();
    	   return newb;
       }
       
       public static Bitmap createCircleBitmap(Context context,int widthHeight,int borderLineWidth){
    	   Bitmap avator;
           Resources rs=context.getResources();
           avator=BitmapFactory.decodeResource(rs,R.drawable.b);
//           Bitmap scaledAvator = scaleBitmapToWidth(avator, widthHeight);
           Bitmap mask = makeMask(widthHeight, widthHeight);

           Bitmap bm = Bitmap.createBitmap(widthHeight, widthHeight, Bitmap.Config.ARGB_8888);
           Canvas c = new Canvas(bm);
           Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
           c.drawBitmap(mask, 0, 0, p);
           p.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));//src_in使其获得它们的交集
//           c.drawBitmap(scaledAvator, 0, 0, p);
           c.drawBitmap(avator, 0, 0, p);
           p.setXfermode(null);

           Bitmap border = makeBorder(widthHeight, widthHeight, borderLineWidth);
           c.drawBitmap(border, 0, 0, p);

           return bm;
    	   
       }
       
       public static Bitmap scaleBitmapToWidth(Bitmap bitmap, int width) {
           Matrix matrix = new Matrix();
           float scale = (float) width / bitmap.getWidth();
           matrix.setScale(scale, scale);
           return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);

       }
       
       private static Bitmap makeMask(int w, int h) {
           Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
           Canvas c = new Canvas(bm);
           Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);

           p.setColor(0xFF000000);
           float leftTop = w * 5 / 100;
           float rightBottom = h * 95 / 100;
           c.drawOval(new RectF(leftTop, leftTop, rightBottom, rightBottom), p);
           return bm;
       }
       
       private static Bitmap makeBorder(int w, int h, int borderLineWidth) {
           Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
           Canvas c = new Canvas(bm);
           Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);

           p.setColor(0xff0298e0);
           p.setStrokeWidth(borderLineWidth);
           p.setStyle(Style.STROKE);
           float leftTop = w * 5 / 100;
           float rightBottom = h * 95 / 100;
           c.drawOval(new RectF(leftTop, leftTop, rightBottom, rightBottom), p);
           return bm;
       }
}
