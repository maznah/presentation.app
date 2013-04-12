package com.example.touchpoint;

import android.annotation.SuppressLint;
import android.app.*;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.*;

public class SplashActivity extends Activity {
	
	private Thread mSplashThread;
	private Activity current;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		current = this;
		setContentView(R.layout.splash_screen);
	
        final SplashActivity SplashScreen = this;   
        mSplashThread =  new Thread(){
			@Override
            public void run(){
                try {
                    synchronized(this){
                        wait(3000); // 3 seconds delay
                    }
                }
                catch(InterruptedException ex){                    
                }

                finish();
                
                Intent intent = new Intent();
                intent.setClass(SplashScreen, MainActivity.class);
                startActivity(intent);
            }
        };
        mSplashThread.start(); 
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN)
        {
            synchronized(mSplashThread){
                mSplashThread.notifyAll();
            }
        }
        return true;
	}
	
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		delayedHide(100);
	};
	
    Handler mHideHandler = new Handler();
    Runnable mHideRunnable = new Runnable() {
		@SuppressLint("InlinedApi")
		@Override
        public void run() {
        	current.findViewById(R.id.textView1).setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE | View.SYSTEM_UI_FLAG_FULLSCREEN);
        }
    };

    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }
}
