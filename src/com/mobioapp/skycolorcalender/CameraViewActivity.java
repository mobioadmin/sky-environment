package com.mobioapp.skycolorcalender;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mobioapp.skycolorcalender.util.LocationHelper;
import com.mobioapp.skycolorcalender.util.ProcessColorData;
import com.mobioapp.skycolorcalender.util.ShowCamera;

public class CameraViewActivity extends Activity {

	private Camera cameraObject;
	private ShowCamera showCamera;
	private TextView colorTextView;
	private FrameLayout cameraPreview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.camera_view);
		colorTextView = (TextView) findViewById(R.id.sky_color);
		cameraObject = ShowCamera.isCameraAvailiable();
		showCamera = new ShowCamera(this, cameraObject);
		cameraPreview = (FrameLayout) findViewById(R.id.camera_preview);
		cameraPreview.addView(showCamera);

	}

	public void captureButtonEvent(View view) {

		cameraObject.takePicture(null, null, capturedIt);

	}

	public void shareColorWithGPSAndTime() {

		LocationHelper helper = new LocationHelper(this, false);

		if (helper.checkIfAnyGPSProviderEnabled()) {

			Location location = helper.getLocation();

		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mmZ",
				Locale.US);
		Log.i("sdf", "" + sdf.format(new Date()));

	}

	private PictureCallback capturedIt = new PictureCallback() {
		public void onPictureTaken(byte[] data, Camera camera) {

			Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);

			new ProcessColorData(colorTextView).execute(bitmap);

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
