package com.mobioapp.skycolorcalender;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.mobioapp.skycolorcalender.util.DownloadStatisticsAsyncTask;

public class SkyCalenderActivity extends Activity {

	private LinearLayout layout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sky_calender);

		layout = (LinearLayout) findViewById(R.id.layout);
		
		new DownloadStatisticsAsyncTask(layout,this).execute("http://mobioapp.net/apps/sky_cam/json.php");

//		addRowInLayout();
	}



}