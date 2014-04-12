package com.mobioapp.skycolorcalender;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.mobioapp.skycolor.R;

public class MainActivity extends Activity {

	private Camera cameraObject;
	private ShowCamera showCamera;
	private ImageView pic;

	public static Camera isCameraAvailiable() {
		Camera object = null;
		try {
			object = Camera.open();
		} catch (Exception e) {
		}
		return object;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		pic = (ImageView) findViewById(R.id.imageView1);
		cameraObject = isCameraAvailiable();
		showCamera = new ShowCamera(this, cameraObject);
		FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
		preview.addView(showCamera);
	}

	public void snapIt(View view) {
		cameraObject.takePicture(null, null, capturedIt);
	}

	private PictureCallback capturedIt = new PictureCallback() {

		@Override
		public void onPictureTaken(byte[] data, Camera camera) {

			Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
			
			getColor(bitmap);
			
//			Buffer imageBuffer;
//			
//			bitmap.copyPixelsToBuffer(imageBuffer);
			if (bitmap == null) {
				Toast.makeText(getApplicationContext(), "not taken",
						Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(getApplicationContext(), "taken",
						Toast.LENGTH_SHORT).show();
			}
			cameraObject.release();
		}
	};
	
	
	private void getColor(Bitmap bmp){
		
		long redBucket = 0;
		long greenBucket = 0;
		long blueBucket = 0;
		long pixelCount = 0;
	  bmp = bmp.copy(Bitmap.Config.ARGB_8888, true);  
	  Log.i("arrayLength",""+bmp.getWidth()*bmp.getHeight());
	    int intArray[] = new int[bmp.getWidth()*bmp.getHeight()];  
	    bmp.getPixels(intArray, 0, bmp.getWidth(), 0, 0, bmp.getWidth(), bmp.getHeight());
	    
	    
	   
	    
		for (int y = 0; y < intArray.length; y++)
		{
//		       
//		        pixelCount++;
//		        redBucket += Color.red(intArray[y]);
//		        greenBucket += Color.green(intArray[y]);
//		        blueBucket += Color.blue(intArray[y]);
//		        // does alpha matter?
//		   
		}
//		Log.i("red",""+redBucket/pixelCount);
//		Log.i("green",""+greenBucket/pixelCount);
//		Log.i("blue",""+blueBucket/pixelCount);
		
		
//
//		int averageColor = Color.rgb((int)(redBucket / pixelCount),
//				(int) (greenBucket / pixelCount),
//				(int) (blueBucket / pixelCount));
//		
//	Log.i("avgColor",""+averageColor);
		
		
	}
	
	
	private void getAverageColor(Bitmap bitmap){
		long redBucket = 0;
		long greenBucket = 0;
		long blueBucket = 0;
		long pixelCount = 0;

		for (int y = 0; y < bitmap.getHeight(); y++)
		{
		    for (int x = 0; x < bitmap.getWidth(); x++)
		    {
		        Color c;// =bitmap.getPixel(x, y);

		       
		        pixelCount++;
//		        redBucket += Color.red(c);
//		        greenBucket += Color.green(c);
//		        blueBucket += Color.blue(c);
		        // does alpha matter?
		    }
		}

//		Color averageColor = Color.rgb(redBucket / pixelCount,
//		                                greenBucket / pixelCount,
//		                                blueBucket / pixelCount);
//		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
