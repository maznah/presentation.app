package com.example.touchpoint;


import android.content.*;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.*;

public class PresentationView extends SurfaceView implements SurfaceHolder.Callback {

	private int viewWidth = 0;
	private int viewHeight = 0;
	private Canvas canvas;
	
	public PresentationView(Context context) {
		super(context);
		
		this.setBackgroundColor(Color.WHITE);
		
		this.setDrawingCacheBackgroundColor(Color.WHITE);
		this.setDrawingCacheEnabled(true);
		this.setDrawingCacheQuality(DRAWING_CACHE_QUALITY_HIGH);
		getHolder().addCallback(this);
	}
	
	public PresentationView(Context context, AttributeSet attrs) {
		super(context,attrs);
		
		this.setBackgroundColor(Color.WHITE);
		
		this.setDrawingCacheBackgroundColor(Color.WHITE);
		this.setDrawingCacheEnabled(true);
		this.setDrawingCacheQuality(DRAWING_CACHE_QUALITY_HIGH);
		getHolder().addCallback(this);
	}
	
	public PresentationView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		
		this.setBackgroundColor(Color.WHITE);
				
		this.setDrawingCacheBackgroundColor(Color.WHITE);
		this.setDrawingCacheEnabled(true);
		this.setDrawingCacheQuality(DRAWING_CACHE_QUALITY_HIGH);
		getHolder().addCallback(this);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		viewWidth = width;
		viewHeight = height;
		
		canvas = holder.lockCanvas();
		
		if(canvas!=null){
			Paint paint = new Paint();
			paint.setAntiAlias(true);
			paint.setStyle(Paint.Style.FILL);
			paint.setARGB(255, 255, 255, 255);
			canvas.drawRect(new Rect(0,0,viewWidth,viewHeight), paint);
		}
		
		holder.unlockCanvasAndPost(canvas);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
	}
}
