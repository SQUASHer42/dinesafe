package com.example.joshhan.dinesafe;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by panda on 2017-05-29.
 */

public class Pin extends AppCompatActivity{
    private Location location;
    private String label;
    private SupportMapFragment mapFragment;
    private float zoomLevel;

    public Pin(Location l, String s, SupportMapFragment m,float z){
        location = l;
        label = s;
        mapFragment = m;
        zoomLevel = z;
    }

    public void moveTheCamera(){
        LatLng userLocation = new LatLng(location.getLatitude(), location.getLongitude());
        GoogleMap map = mapFragment.getMap();
        map.addMarker(new MarkerOptions().position(userLocation).title(label));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, zoomLevel));
    }

}