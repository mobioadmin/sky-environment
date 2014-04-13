package com.mobioapp.skycolorcalender.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import android.annotation.SuppressLint;
import android.location.Location;
import android.os.AsyncTask;
import android.util.Log;

@SuppressLint("NewApi")
public class UploadInfoAsyncTask extends AsyncTask<String, Integer, Boolean> {

	private String colorName;
	private int r;
	private int g;
	private int b;
	private Location location;
	private String dateTime;

	public UploadInfoAsyncTask(String colorName, int r, int g, int b,
			Location location, String dateTime) {

		this.colorName = colorName;
		this.r = r;
		this.g = g;
		this.b = b;
		this.location = location;
		this.dateTime = dateTime;
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}

	@Override
	protected Boolean doInBackground(String... params) {
		// TODO Auto-generated method stub

		List<NameValuePair> list = new ArrayList<NameValuePair>();

		String urlStr = params[0];

		list = uploadInfo(list, "location_lat",
				String.valueOf(location.getLatitude()));
		list = uploadInfo(list, "location_long",
				String.valueOf(location.getLatitude()));
		list = uploadInfo(list, "location_alt",
				String.valueOf(location.getAltitude()));
		
//		list = uploadInfo(list, "location_lat",
//				"lat");
//		list = uploadInfo(list, "location_long",
//			"long");
//		list = uploadInfo(list, "location_alt",
//				"alt");
		
		
		list = uploadInfo(list, "datetime", dateTime);
		list = uploadInfo(list, "color", colorName);
		list = uploadInfo(list, "r", String.valueOf(r));
		list = uploadInfo(list, "g", String.valueOf(g));
		list = uploadInfo(list, "b", String.valueOf(b));

		String result = UploadInfoUsingPost(urlStr, list);
		
		Log.i("echo",result);

		if (result.toLowerCase().equals("success"))
			return true;
		else
			return false;

		// return null;
	}

	private String UploadInfoUsingPost(String urlStr, List<NameValuePair> list) {

		StringBuilder sb;
		InputStream is = null;
		String result = "";
		
		Log.i("url",urlStr);

		try {

			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(urlStr);
			httppost.setEntity(new UrlEncodedFormEntity(list));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();

		} catch (Exception e) {
			Log.e("log_tag", "Error in http connection" + e.toString());
		}

		// convert response to string
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 8);
			sb = new StringBuilder();
			sb.append(reader.readLine() + "\n");
			String line = "0";
			// System.out.println("Result2: " + result);
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}

			is.close();
			result = sb.toString();
			
			Log.i("result",result);

		} catch (Exception e) {
			Log.e("log_tag", "Error converting result " + e.toString());
		}

		return result;

	}

	private List<NameValuePair> uploadArrayInfo(List<NameValuePair> list,
			String getName, String[] array) {
		for (int j = 0; j < array.length; j++) {
			list.add(new BasicNameValuePair(getName + "[" + j + "]", array[j]));

		}

		return list;

	}

	private List<NameValuePair> uploadInfo(List<NameValuePair> list,
			String getName, String info) {

		list.add(new BasicNameValuePair(getName, info));

		return list;
	}

	@Override
	protected void onPostExecute(Boolean result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
	}

}
