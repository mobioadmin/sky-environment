package com.mobioapp.skycolorcalender.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.location.Location;
import android.os.AsyncTask;
import android.util.Log;

public class UploadInfoAsyncTask extends AsyncTask<String, Integer, String> {

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
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub

		List<NameValuePair> list = new ArrayList<NameValuePair>();

		String urlStr = params[0];
		
		list=uploadInfo(list, "location_lat", String.valueOf( location.getLatitude()));
		list=uploadInfo(list, "location_long", String.valueOf( location.getLatitude()));
		list=uploadInfo(list, "location_alt", String.valueOf( location.getAltitude()));
		list=uploadInfo(list, "datetime", dateTime);
		list=uploadInfo(list, "color", colorName);
		list=uploadInfo(list, "r",String.valueOf( r));
		list=uploadInfo(list, "g", String.valueOf( g));
		list=uploadInfo(list, "b", String.valueOf( b));

		String result = UploadInfoUsingPost(urlStr, list);

		return null;
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

	private String UploadInfoUsingPost(String urlStr, List<NameValuePair> list) {

		StringBuilder sb;
		InputStream is = null;
		String result = "";

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
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
	}

}
