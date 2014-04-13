package com.mobioapp.skycolorcalender.util;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

public class LocationHelper implements LocationListener {

	private Activity context;
	private Boolean isGPSEnabled;
	private Boolean isNetworkEnabled;
	private LocationManager locationManager;
	private Location location;
	private Boolean activateLocationListener;

	public LocationHelper(Activity context, Boolean activateLocationListener) {
		// TODO Auto-generated constructor stub

		this.context = context;
		this.activateLocationListener = activateLocationListener;
		
		locationManager = (LocationManager) context
				.getSystemService(Activity.LOCATION_SERVICE);

		if (this.activateLocationListener)
			activateLocationListener();
	}

	private void activateLocationListener() {

		if (isGPSEnabled)
			locationManager.requestLocationUpdates(
					LocationManager.GPS_PROVIDER, 0, 0, this);
		else if (isNetworkEnabled)
			locationManager.requestLocationUpdates(
					LocationManager.NETWORK_PROVIDER, 0, 0, this);

	}

	public Location getLocation() {

		

		checkIfAnyOfGPSProviderEnabled();

		if (isGPSEnabled)
			location = locationManager
					.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		else if (isNetworkEnabled)
			location = locationManager
					.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		else
			location = null;
		
		Log.i("lovc",""+location.getLatitude());

		return location;
	}

	public Boolean checkIfAnyOfGPSProviderEnabled() {

		isGPSEnabled = false;
		isNetworkEnabled = false;

		if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
			isGPSEnabled = true;
		if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))
			isNetworkEnabled = true;

		if (isGPSEnabled || isNetworkEnabled)
			return true;
		else
			return false;

	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub

		this.location = location;

	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub

	}

}
