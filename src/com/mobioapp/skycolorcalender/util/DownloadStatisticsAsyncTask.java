package com.mobioapp.skycolorcalender.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.client.ClientProtocolException;

import android.os.AsyncTask;

public class DownloadStatisticsAsyncTask extends
		AsyncTask<String, Integer, Boolean> {
	
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}

	@Override
	protected Boolean doInBackground(String... urlStr) {
		// TODO Auto-generated method stub
		
		String result=downloadData(urlStr[0]);
		return null;
	}
	
	@Override
	protected void onPostExecute(Boolean result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
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
