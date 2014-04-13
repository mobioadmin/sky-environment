package com.mobioapp.skycolorcalender;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class SkyCalenderActivity extends Activity {

	private LinearLayout layout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sky_calender);

		layout = (LinearLayout) findViewById(R.id.layout);

		addRowInLayout();
	}

	private void addRowInLayout() {

		for (int i = 0; i < 10; i++) {

			LinearLayout ll = new LinearLayout(this);

			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 0);
			
			params.leftMargin=20;

			LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
					0, LayoutParams.WRAP_CONTENT, 50);
			
			
			params2.setMargins(15, 15, 15, 15);

			TextView time = new TextView(this);
			TextView color = new TextView(this);

			time.setText("time");
			color.setText("color");
			
			time.setWidth(0);
			

			time.setLayoutParams(params2);
			color.setLayoutParams(params2);

			ll.setLayoutParams(params);

			ll.setOrientation(LinearLayout.HORIZONTAL);

			ll.addView(time);
			ll.addView(color);

			layout.addView(ll);
		}

	}

}