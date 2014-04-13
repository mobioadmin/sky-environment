package com.mobioapp.skycolorcalender;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainMenuActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.main_activity);
	}

	public void skyColorShareButtonEvent(View view) {

		startActivity(new Intent(this, CameraViewActivity.class));

	}

	public void showSkyCalender(View view) {

		startActivity(new Intent(this, SkyCalender.class));

	}

}
