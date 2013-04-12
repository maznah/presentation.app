package com.example.touchpoint;

import java.util.Locale;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.*;
import android.os.*;
import android.support.v4.app.*;
import android.support.v4.view.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends FragmentActivity {

	SectionsPagerAdapter mSectionsPagerAdapter;
	ViewPager mViewPager;
	
	private Settings appSettings = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		
		appSettings = new Settings("com.example.touchpoint");

		mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

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
    		showSettings();
    		return true;
    	case R.id.menu_exit:
    		doExit(this);
    		return true;
    	}
    	return super.onOptionsItemSelected(item);
	}

	private void showSettings() {
		Intent showSettings = new Intent(this,SettingsActivity.class);
		startActivity(showSettings);
	}

	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			Fragment fragment = new TabFragment();
			Bundle args = new Bundle();
			args.putInt(TabFragment.ARG_SECTION_NUMBER, position + 1);
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public int getCount() {
			return 2;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_tab_authoring).toUpperCase(l);
			case 1:
				return getString(R.string.title_tab_present).toUpperCase(l);
			}
			return null;
		}
	}

	public static class TabFragment extends Fragment {

		public static final String ARG_SECTION_NUMBER = "section_number";

		public TabFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main_dummy,container, false);
			TextView dummyTextView = (TextView) rootView.findViewById(R.id.section_label);
			dummyTextView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));
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
	
	protected void onResume() {
		super.onResume();
		if(appSettings!=null) appSettings.readSettings(this);
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
}