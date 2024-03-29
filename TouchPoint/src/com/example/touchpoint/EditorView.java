package com.example.touchpoint;

import android.content.*;
import android.graphics.*;
import android.util.*;
import android.view.*;

public class EditorView extends SurfaceView implements SurfaceHolder.Callback {

	private int viewWidth, viewHeight; 
	private Globals global = new Globals();
	
	public EditorView(Context context) {
		super(context);
		setWillNotDraw(false);
		
		this.setBackgroundColor(Color.WHITE);
		
		this.setDrawingCacheBackgroundColor(Color.WHITE);
		this.setDrawingCacheEnabled(true);
		this.setDrawingCacheQuality(DRAWING_CACHE_QUALITY_HIGH);
		getHolder().addCallback(this);
	}
	
	public EditorView(Context context, AttributeSet attrs) {
		super(context,attrs);
		setWillNotDraw(false);
		
		this.setBackgroundColor(Color.WHITE);
		
		this.setDrawingCacheBackgroundColor(Color.WHITE);
		this.setDrawingCacheEnabled(true);
		this.setDrawingCacheQuality(DRAWING_CACHE_QUALITY_HIGH);
		getHolder().addCallback(this);
	}
	
	public EditorView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setWillNotDraw(false);
		
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
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if(canvas!=null) customDraw(canvas);
		
		if(global!=null && canvas!=null){
			if(global.currentPresentation!=null){
				global.currentPresentation.draw(canvas,viewWidth,viewHeight);
			}
		}
	}

	private void customDraw(Canvas canvas) {
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setStyle(Paint.Style.FILL);
		paint.setARGB(255, 255, 255, 255);
		canvas.drawRect(new Rect(0,0,viewWidth,viewHeight), paint);
		
		paint.setARGB(230, 204, 204, 204);
		paint.setStrokeWidth(1);
		paint.setStyle(Paint.Style.STROKE);
		canvas.drawRoundRect(new RectF(12,12,viewWidth-12,viewHeight-12), 10f , 10f, paint);
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
	}
	
}
