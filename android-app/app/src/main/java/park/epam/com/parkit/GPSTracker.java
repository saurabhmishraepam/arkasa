package park.epam.com.parkit;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.maps.DirectionsApi;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.Duration;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;

import org.joda.time.DateTime;

import java.io.IOException;

import park.epam.com.parkit.cached.EmployeeCached;
import park.epam.com.parkit.constants.GoogleToken;
import park.epam.com.parkit.constants.OfficeLoc;
import park.epam.com.parkit.dto.LocationRequestDto;

public class GPSTracker extends Service implements LocationListener {

    private final Context mContext;
    // flag for GPS status
    boolean isGPSEnabled = false;
    // flag for network status
    boolean isNetworkEnabled = false;
    // flag for GPS status
    boolean canGetLocation = false;
    Location location; // location
    double latitude; // latitude
    double longitude; // longitude
    String distanceAll;
    public LocationRequestDto locationRequestDto;

    public LocationManager getLocationManager() {
        return locationManager;
    }

    public void setLocationManager(LocationManager locationManager) {
        this.locationManager = locationManager;
    }

    // The minimum distance to change Updates in meters
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters

    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute

    // Declaring a Location Manager
    protected LocationManager locationManager;

    public GPSTracker(Context context) {
        this.mContext = context;
        getLocation();

    }

    public Location getLocation() {


        try {
            System.out.print("mContext :"+mContext);
            if (mContext != null) {
                locationManager = (LocationManager) mContext.getSystemService(LOCATION_SERVICE);

                // getting GPS status
                isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

                // getting network status
                isNetworkEnabled = locationManager
                        .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

                if (!isGPSEnabled && !isNetworkEnabled) {
                    // no network provider is enabled
                } else {
                    this.canGetLocation = true;
                    // First get location from Network Provider
                    if (isNetworkEnabled) {
                        locationManager.requestLocationUpdates(
                                LocationManager.NETWORK_PROVIDER,
                                MIN_TIME_BW_UPDATES,
                                MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

                        Log.d("Network", "Network");
                        if (locationManager != null) {
                            location = locationManager
                                    .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                            if (location != null) {
                                latitude = location.getLatitude();
                                longitude = location.getLongitude();
                            }
                        }
                    }
                    // if GPS Enabled get lat/long using GPS Services
                    if (isGPSEnabled) {
                        if (location == null) {
                            locationManager.requestLocationUpdates(
                                    LocationManager.GPS_PROVIDER,
                                    MIN_TIME_BW_UPDATES,
                                    MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

                            Log.d("GPS Enabled", "GPS Enabled");
                            if (locationManager != null) {
                                location = locationManager
                                        .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                                if (location != null) {
                                    latitude = location.getLatitude();
                                    longitude = location.getLongitude();
                                }
                            }
                        }
                    }
                }
            }
            } catch(Exception e){
                e.printStackTrace();
            }

        if (location != null) {
            distanceAll = "Distance: " + getDurationForRoute(location, "", "") + "\nLat:" + location.getLatitude() + "\nLong:" + location.getLongitude();
        }


        return location;
    }

    /**
     * Stop using GPS listener
     * Calling this function will stop using GPS in your app
     */

    public void stopUsingGPS() {
        if (locationManager != null) {
            locationManager.removeUpdates(GPSTracker.this);
        }
    }

    public double getLatitude() {
        if (location != null) {
            latitude = location.getLatitude();
        }
        // return latitude
        return latitude;
    }

    public double getLongitude() {
        if (location != null) {
            longitude = location.getLongitude();
        }

        // return longitude
        return longitude;
    }

    public boolean canGetLocation() {
        return this.canGetLocation;
    }

    public void showSettingsAlert() {
        if(mContext != null) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);

            // Setting Dialog Title
            alertDialog.setTitle("GPS is settings");

            // Setting Dialog Message
            alertDialog.setMessage("GPS is not enabled. Do you want to go to settings menu?");

            // On pressing Settings button
            alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    mContext.startActivity(intent);
                }
            });

            // on pressing cancel button
            alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            // Showing Alert Message
            alertDialog.show();
        }
    }


    public String getDurationForRoute(Location loc, String origin, String destination) {
        // - We need a context to access the API


        LocationRequestDto locationRequest = new LocationRequestDto();
        GeoApiContext geoApiContext = new GeoApiContext.Builder()
                .apiKey(GoogleToken.apitoken)
                .build();


        DateTime now = new DateTime();

        // - Perform the actual request
        DirectionsResult directionsResult = null;
        try {

            LatLng originAdd = new LatLng(loc.getLatitude(), loc.getLongitude());
            LatLng destinationAdd = new LatLng(OfficeLoc.lat, OfficeLoc.log);
            DistanceMatrixApiRequest req = DistanceMatrixApi.newRequest(geoApiContext);
            DistanceMatrix trix = req.origins(originAdd)
                    .destinations(destinationAdd)
                    .mode(TravelMode.DRIVING).departureTime(new DateTime())
                    .avoid(DirectionsApi.RouteRestriction.HIGHWAYS)
                    .language("en-IN")
                    .await();
            String distApart = "Time to Reach: " + trix.rows[0].elements[0].durationInTraffic + "\nDistance: " + trix.rows[0].elements[0].distance + "\n All Data" + trix.rows[0].elements[0];
            locationRequest.setCurrentDistanceInKms(trix.rows[0].elements[0].distance.inMeters);
            locationRequest.setTimeToReachOffice(trix.rows[0].elements[0].durationInTraffic.inSeconds);
            locationRequest.setLastUpdated(now.getMillis());
            locationRequest.setLat(loc.getLatitude());
            locationRequest.setLang(loc.getLongitude());
            this.locationRequestDto = locationRequest;
            return distApart;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

       /* DirectionsRoute route = directionsResult.routes[0];
        DirectionsLeg leg = route.legs[0];
        Duration duration = leg.duration;
        return duration.humanReadable;*/

        // - Parse the result

        return null;
    }

    public void callTracker(){
        Log.d("Calling tracker","..");
        GPSTracker gps = new GPSTracker( this);
        String empId = getRegisterdEmp();
        Log.d("Employee Id :",empId);
        LocationRequestDto locationRequestDto = new LocationRequestDto();
        locationRequestDto.setEmpId(empId);
        gps.locationRequestDto = locationRequestDto;
        // check if GPS enabled
        if (gps.canGetLocation()) {
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            Log.d("Lat:",String.valueOf(latitude));
            Log.d("Long:",String.valueOf(longitude));
            Toast.makeText(getApplicationContext(), gps.distanceAll, Toast.LENGTH_LONG).show();
        } else {
            gps.showSettingsAlert();
        }

    }
    
    public String getRegisterdEmp() {
        Log.d("Registerd emp :", "");
        EmployeeCached employeeCached = new EmployeeCached();
        Log.d("Employee Id cache", employeeCached.getEmployeeId());
        return employeeCached.getEmployeeId();
    }
    @Override
    public void onLocationChanged(Location location) {
        Log.d("On Location change","...");
       // callTracker();
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("On provider disabled.","");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("On provider enabled.","");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("On Status change.","");
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }
}
