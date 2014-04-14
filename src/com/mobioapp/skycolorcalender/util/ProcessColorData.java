package com.mobioapp.skycolorcalender.util;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

@SuppressLint("NewApi")
public class ProcessColorData extends AsyncTask<Bitmap, Integer, String> {

	private TextView tv;
//	private String colorName;
//	private int r;
//	private int g;
//	private int b;
	private ColorInfo colorInfo;

	public ProcessColorData(TextView tv, ColorInfo colorInfo) {
		this.tv = tv;
//		this.setColorInfo(new ColorInfo());
		this.colorInfo=new ColorInfo();
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
//		setColorinfo(new ColorInfo());
		
	

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


		ColorUtil util = new ColorUtil();

//		r = (int) (redBucket / pixelCount);
//		g = (int) (greenBucket / pixelCount);
//		b = (int) (blueBucket / pixelCount);
		
		getColorinfo().setB((int) (blueBucket / pixelCount));
		getColorinfo().setR((int) (redBucket / pixelCount));
		getColorinfo().setG((int) (greenBucket / pixelCount));

		getColorinfo().setColorName(util.getColorNameFromRgb(getColorinfo().getR(), getColorinfo().getG(), getColorinfo().getB()));

		return getColorinfo().colorName;

	}

	private int rgb(int red, int green, int blue) {
		return (0xFF << 24) | (red << 16) | (green << 8) | blue;
	}
	
	public ColorInfo getColorinfo() {
		return getColorInfo();
	}

	public void setColorinfo(ColorInfo colorinfo) {
		this.setColorInfo(colorinfo);
	}

	public ColorInfo getColorInfo() {
		return colorInfo;
	}

	public void setColorInfo(ColorInfo colorInfo) {
		this.colorInfo = colorInfo;
	}

	public class ColorInfo{
		private String colorName;
		private int r;
		private int g;
		private int b;
		public String getColorName() {
			return colorName;
		}
		public void setColorName(String colorName) {
			this.colorName = colorName;
		}
		public int getR() {
			return r;
		}
		public void setR(int r) {
			this.r = r;
		}
		public int getG() {
			return g;
		}
		public void setG(int g) {
			this.g = g;
		}
		public int getB() {
			return b;
		}
		public void setB(int b) {
			this.b = b;
		}
		
		
		
		
	}

}