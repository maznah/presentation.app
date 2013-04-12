package com.example.touchpoint.objectmodel;

import android.content.*;
import android.graphics.*;
import android.util.*;
import android.view.*;

public class EditorView extends SurfaceView implements SurfaceHolder.Callback {

	private Canvas canvas;
	private int viewWidth, viewHeight; 
	
	public EditorView(Context context) {
		super(context);
		getHolder().addCallback(this);
	}
	
	public EditorView(Context context, AttributeSet attrs) {
		super(context,attrs);
		getHolder().addCallback(this);
	}
	
	public EditorView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
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
