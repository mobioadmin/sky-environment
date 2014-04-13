package com.mobioapp.skycolorcalender;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Camera cameraObject;
	private ShowCamera showCamera;
	private TextView colorTextView;
	private FrameLayout preview;

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
		colorTextView = (TextView) findViewById(R.id.sky_color);
		cameraObject = isCameraAvailiable();
		showCamera = new ShowCamera(this, cameraObject);
		 preview = (FrameLayout) findViewById(R.id.camera_preview);
		preview.addView(showCamera);

	}

	public void snapIt(View view) {

		
		cameraObject.takePicture(null, null, capturedIt);

	}

	private PictureCallback capturedIt = new PictureCallback() {
		public void onPictureTaken(byte[] data, Camera camera) {

			Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
			
			new ProcessColorData(colorTextView).execute(bitmap);

//			getAverageColor(bitmap);

			if (bitmap == null) {
				Toast.makeText(getApplicationContext(), "not taken",
						Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(getApplicationContext(), "taken",
						Toast.LENGTH_SHORT).show();
			}
			
			camera.startPreview();

		}
	};

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
