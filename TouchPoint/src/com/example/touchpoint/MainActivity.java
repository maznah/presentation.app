package com.example.touchpoint;

import java.util.Locale;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.FragmentTransaction;
import android.content.*;
import android.os.*;
import android.support.v4.app.*;
import android.support.v4.view.*;
import android.view.*;

public class MainActivity extends FragmentActivity implements ActionBar.TabListener {

	SectionsPagerAdapter mSectionsPagerAdapter;
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		
		mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		
		mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				actionBar.setSelectedNavigationItem(position);
			}
		});	
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			actionBar.addTab(actionBar.newTab().setText(mSectionsPagerAdapter.getPageTitle(i)).setTabListener(this));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
    	switch(item.getItemId()){
    	case R.id.menu_settings:
    		//showSettings();
    		return true;
    	case R.id.menu_exit:
    		doExit(this);
    		return true;
    	}
    	return super.onOptionsItemSelected(item);
	}
	
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			Fragment fragment = null;
			
			switch(position){
			case 0:{
				fragment = new PresentationFragment();
				return fragment;
			}
			case 1:{
				fragment = new EditorFragment();
				return fragment;
			}
			case 2:{
				fragment = new EditorFragment();
				return fragment;
			}
			case 3:{
				fragment = new EditorFragment();
				return fragment;
			}
			case 4:{
				fragment = new EditorFragment();
				return fragment;
			}
			case 5:{
				fragment = new EditorFragment();
				return fragment;
			}
			case 6:{
				fragment = new EditorFragment();
				return fragment;
			}	
			}
			return null;
		}

		@Override
		public int getCount() {
			return 7;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:return getString(R.string.title_tab_presentations_list).toUpperCase(l);
			case 1:return getString(R.string.title_tab_layout).toUpperCase(l);
			case 2:return getString(R.string.title_tab_images).toUpperCase(l);
			case 3:return getString(R.string.title_tab_audio).toUpperCase(l);
			case 4:return getString(R.string.title_tab_video).toUpperCase(l);
			case 5:return getString(R.string.title_tab_text).toUpperCase(l);
			case 6:return getString(R.string.title_tab_button).toUpperCase(l);
			}
			return null;
		}
	}

	public static class EditorFragment extends Fragment {
		
		public EditorFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.editor_layout,container, false);
			
			return rootView;
		}
	}
	
	public static class PresentationFragment extends Fragment {
		
		public PresentationFragment() {
		}
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.presentation_layout,container, false);
			
			return rootView; 
		}
	}
	
	private void doExit(Context context){
		CloseApplicationDialog(this,getResources().getString(R.string.dlgExit),getResources().getString(R.string.dlgCancel),"Warning - Are you sure you want to exit the Application?");
	}
	
	public void CloseApplication(){
		finish();
	}
	
	public void CloseApplicationDialog(Context context, String PositiveButtonCaption, String NegativeButtonCaption ,String message){
		
		Builder template = new AlertDialog.Builder(context);
		template.setCancelable(true);
		template.setMessage(message);
		template.setTitle(R.string.title_confirmation);
		template.setIcon(android.R.drawable.ic_dialog_alert);
		template.setNegativeButton(NegativeButtonCaption, new DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}});
		template.setPositiveButton(PositiveButtonCaption, new DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				CloseApplication();
			}
		});
		template.setOnCancelListener(new DialogInterface.OnCancelListener() {
			@Override
			public void onCancel(DialogInterface dialog) {
				dialog.dismiss();
			}
		});
		AlertDialog dialog = template.create();
		dialog.setCanceledOnTouchOutside(true);
		dialog.show();
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
			mViewPager.findViewById(R.id.pager).setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE | View.SYSTEM_UI_FLAG_FULLSCREEN);
        }
    };

    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {	
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	}
}