package com.mobioapp.skycolorcalender.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

@SuppressLint("NewApi")
public class DownloadStatisticsAsyncTask extends
		AsyncTask<String, Integer, String> {
	private LinearLayout layout;
	private Activity context;

	public DownloadStatisticsAsyncTask(LinearLayout layout, Activity context) {
		this.layout = layout;
		this.context = context;

	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}

	@Override
	protected String doInBackground(String... urlStr) {
		// TODO Auto-generated method stub

		String result = downloadData(urlStr[0]);
		return result;
	}

	@Override
	protected void onPostExecute(String response) {
		// TODO Auto-generated method stub
		super.onPostExecute(response);
		ArrayList<DatabaseEntity> arlist = parseData(response);

		addRowInLayout(arlist);
	}

	private void addRowInLayout(ArrayList<DatabaseEntity> arlist) {

		int max = arlist.size() < 11 ? arlist.size() : 10;

		for (int i = 0; i < max; i++) {

			LinearLayout ll = new LinearLayout(context);

			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 0);

//			params.leftMargin = 20;

			LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
					0, LayoutParams.WRAP_CONTENT, 33);

			params2.setMargins(15, 15, 15, 15);

			TextView time = new TextView(context);
			TextView color = new TextView(context);
			TextView latlong = new TextView(context);

			DatabaseEntity dbEn = arlist.get(i);

			if (i == 0) {
				time.setText("Time");
				color.setText("Color");
				latlong.setText("Lat-Long");
			} else {
				time.setText(dbEn.date);
				color.setText(dbEn.color);
				latlong.setText(dbEn.lat + "," + dbEn.lon);

			}
			// time.setWidth(0);

			time.setLayoutParams(params2);
			color.setLayoutParams(params2);
			latlong.setLayoutParams(params2);

			ll.setLayoutParams(params);

			ll.setOrientation(LinearLayout.HORIZONTAL);

			ll.addView(time);
			ll.addView(color);
			ll.addView(latlong);
			if (i == 0)
				ll.setBackgroundColor(Color.GRAY);

			layout.addView(ll);
		}

	}

	private ArrayList<DatabaseEntity> parseData(String response) {

		ArrayList<DatabaseEntity> arlist = new ArrayList<DatabaseEntity>();

		try {
			JSONObject jsonObj = new JSONObject(response);

			JSONArray jsArray = jsonObj.getJSONArray("album");

			for (int j = 0; j < jsArray.length(); j++) {

				DatabaseEntity dbEn = new DatabaseEntity();

				JSONObject jsobj = jsArray.getJSONObject(j);

				dbEn.b = (String) jsobj.get("b");

				dbEn.g = (String) jsobj.get("g");

				dbEn.r = (String) jsobj.get("r");

				dbEn.color = (String) jsobj.get("color");

				dbEn.lat = (String) jsobj.get("location_lat");

				dbEn.lon = (String) jsobj.get("location_long");

				dbEn.alt = (String) jsobj.get("location_alt");

				dbEn.date = (String) jsobj.get("datetime");

				arlist.add(dbEn);

			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return arlist;

	}

	private String downloadData(String urlStr) {

		InputStream inputStream = null;
		URL url = null;
		String responseStr = "";

		try {
			url = new URL(urlStr);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpURLConnection httpURLConnection = null;

		// The line below is where the exception is called
		int response = 0;
		try {
			httpURLConnection = (HttpURLConnection) url.openConnection();
			response = httpURLConnection.getResponseCode();

			if (response == HttpURLConnection.HTTP_OK) {

				// Response successful
				inputStream = httpURLConnection.getInputStream();

				BufferedReader reader = new BufferedReader(
						new InputStreamReader(inputStream));
				StringBuilder sb = new StringBuilder();

				String line = null;

				while ((line = reader.readLine()) != null) {
					sb.append(line + "\n");
				}

				responseStr = sb.toString();

			}

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return responseStr;

	}

}
