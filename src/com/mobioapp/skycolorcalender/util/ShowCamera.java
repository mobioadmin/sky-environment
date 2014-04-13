package com.mobioapp.skycolorcalender.util;

import java.io.IOException;

import android.content.Context;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class ShowCamera extends SurfaceView implements SurfaceHolder.Callback {

	private SurfaceHolder holdMe;
	private Camera theCamera;

	public static Camera isCameraAvailiable() {
		Camera object = null;
		try {
			object = Camera.open();
		} catch (Exception e) {
		}
		return object;
	}

	public ShowCamera(Context context, Camera camera) {
		super(context);
		theCamera = camera;
		holdMe = getHolder();
		holdMe.addCallback(this);
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		try {
			theCamera.setPreviewDisplay(holder);
			theCamera.startPreview();
		} catch (IOException e) {
		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
	}

	
	
}
