package com.example.parkingfinderapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    private double userLat = 0, userLng = 0;
    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listViewParkings);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        // طلب إذن الموقع
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
            return;
        }

        // تحديث الموقع مباشرة
        Location lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (lastLocation != null) {
            userLat = lastLocation.getLatitude();
            userLng = lastLocation.getLongitude();
        }

        // Listener لتحديث الموقع عند تغييره
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                5000, // كل 5 ثواني
                5,    // كل 5 متر
                new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        userLat = location.getLatitude();
                        userLng = location.getLongitude();
                        setupListView(); // تحديث القائمة مع الموقع الجديد
                    }
                    @Override public void onStatusChanged(String provider, int status, Bundle extras) {}
                    @Override public void onProviderEnabled(String provider) {}
                    @Override public void onProviderDisabled(String provider) {}
                });

        setupListView(); // عرض القائمة أول مرة
    }

    private void setupListView() {
        ParkingFinder finder = new ParkingFinder();
        ArrayList<Parking> parkings = finder.getAllParkings();

        // ترتيب حسب الأقرب
        Collections.sort(parkings, (p1, p2) -> Float.compare(
                p1.getDistance(userLat, userLng),
                p2.getDistance(userLat, userLng)
        ));

        ParkingAdapter adapter = new ParkingAdapter(this, parkings, userLat, userLng);
        listView.setAdapter(adapter);
    }
}