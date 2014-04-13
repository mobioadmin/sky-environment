package com.mobioapp.skycolorcalender;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

public class ProcessColorData extends AsyncTask<Bitmap, Integer, String> {

	private TextView tv;

	public ProcessColorData(TextView tv) {
		this.tv = tv;
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		
		tv.setText("Updating Color Info,Wait...");
	}

	@Override
	protected String doInBackground(Bitmap... bitmaps) {
		// TODO Auto-generated method stub

		// getAverageColor(bitmaps[0]);
		return getAverageColor(bitmaps[0]);
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		// TODO Auto-generated method stub
		super.onProgressUpdate(values);

		// values[0].setText(result);
	}

	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		
		tv.setText(result);
	}

	private String getAverageColor(Bitmap bitmap) {
		long redBucket = 0;
		long greenBucket = 0;
		long blueBucket = 0;
		long pixelCount = 0;

		for (int y = 0; y < bitmap.getHeight(); y++) {
			for (int x = 0; x < bitmap.getWidth(); x++) {
				int c = bitmap.getPixel(x, y);

				pixelCount++;
				redBucket += Color.red(c);
				greenBucket += Color.green(c);
				blueBucket += Color.blue(c);
				// does alpha matter?
			}
		}

		Log.i("cc",
				""
						+ rgb(((int) (redBucket / pixelCount)),
								((int) (greenBucket / pixelCount)),
								((int) (blueBucket / pixelCount))));

		ColorUtil util = new ColorUtil();

		return util.getColorNameFromRgb(((int) (redBucket / pixelCount)),
				((int) (greenBucket / pixelCount)),
				((int) (blueBucket / pixelCount)));

	}

	private int rgb(int red, int green, int blue) {
		return (0xFF << 24) | (red << 16) | (green << 8) | blue;
	}

}