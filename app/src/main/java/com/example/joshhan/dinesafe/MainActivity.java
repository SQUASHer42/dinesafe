package com.example.joshhan.dinesafe;

import android.Manifest;
import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;

import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.w3c.dom.Text;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    private static boolean notIntent = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        if (googleApiClient == null)
            googleApiClient = new GoogleApiClient.Builder(this).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).build();
        locationRequest = LocationRequest.create().setPriority(locationRequest.PRIORITY_HIGH_ACCURACY).setInterval(10000).setFastestInterval(1000);


        //new CloudantHandler(cloudantClient, "dinesafe");
        if(notIntent) {
            TextView view = (TextView) findViewById(R.id.name);
            view.setText("Ready   ");
        }
        //view.setText(r.toString());

        Log.d(TAG, "STUPID");

        Intent intent = getIntent();
        if(intent.hasExtra("address")){
            notIntent = false;
            Log.d(TAG, "Went through onCreate");
            onNewIntent(intent);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent){
        TextView pass = (TextView)findViewById(R.id.pass);
        pass.setText(intent.getStringExtra("status"));

        if(intent.getStringExtra("status").toUpperCase().equals("PASS")){
            pass.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.pass));
        }
        if(intent.getStringExtra("status").toUpperCase().equals("CONDITIONAL PASS")){
            pass.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.conditional));
        }
        if(intent.getStringExtra("status").toUpperCase().equals("CLOSED")){
            pass.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.closed));
        }


        TextView name = (TextView)findViewById(R.id.name);
        name.setText(intent.getStringExtra("name"));
        Log.d(TAG, intent.getStringExtra("name"));
        TextView address = (TextView)findViewById(R.id.address);
        address.setText(intent.getStringExtra("address"));


        ArrayList<UsefulReview> usefulReviewArrayList = (ArrayList<UsefulReview>) intent.getSerializableExtra("reviews");

        LinearLayout ll = (LinearLayout) findViewById(R.id.reviews);
        ArrayList<View> results = new ArrayList<View>();
        if(results != null) {
            for (int i = 0; i < usefulReviewArrayList.size(); i++) {
                final TextView v = new TextView(MainActivity.this);
                final UsefulReview u = usefulReviewArrayList.get(i);
                v.setText(u.getComments());
                v.setTextColor(Color.BLACK);
                try {
                    if (u.getSeverity().toCharArray()[0] == 'M')
                        v.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.pass));
                    else if (u.getSeverity().toCharArray()[0] == 'S')
                        v.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.closed));
                    else if (u.getSeverity().toCharArray()[0] == 'N')
                        v.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.grey));
                    else{
                        v.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.back));
                        v.setText(u.getInspectionDate()+ ":\nPass");
                    }
                }
                catch(Exception e) {
                    v.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.back));
                    v.setText(u.getInspectionDate() + ":\nPass");
                }
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                lp.setMargins(30, 15, 30, 15);
                v.setLayoutParams(lp);
                results.add(v);
                ll.addView(results.get(i));
            }
        }
        else{
            TextView v = new TextView(MainActivity.this);
            v.setText("Comments not found");
            v.setTextColor(Color.BLACK);
            results.add(v);
            ll.addView(results.get(0));
        }

        /**if(intent.getStringExtra("severity").toUpperCase().toCharArray()[0] == 'M'){
            comment.setBackground(getDrawable(R.drawable.pass));
        }
        if(intent.getStringExtra("severity").toUpperCase().toCharArray()[0] == 'S'){
            comment.setBackground(getDrawable(R.drawable.closed));
        }**/

        Location location = new Location("");
        double latitude = intent.getDoubleExtra("latitude", 0);
        double longitude = intent.getDoubleExtra("longitude", 0);
        location.setLatitude(longitude);
        location.setLongitude(latitude);
        handleLocation(location, intent.getStringExtra("name"));

        Log.d(TAG, "handle intent");
    }

    @Override
    public void onLocationChanged(Location location) {
        if(notIntent)
            handleLocation(location, "You are here");
    }

    protected void onStart() {
        googleApiClient.connect();
        super.onStart();
    }

    protected void onStop() {
        googleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem menuItem = menu.findItem(R.id.search);
        SearchView searchView =
                (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(new ComponentName(getApplicationContext(), Search.class)));
        return true;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        try{
            Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
            if (lastLocation != null && notIntent) {
                handleLocation(lastLocation, "You are here");
            }
            else {
                LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
                //handleLocation(LocationServices.FusedLocationApi.getLastLocation(googleApiClient));
                //lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);//TODO handle exception when there isn't a lastLocation --> you have to start the location thing yourself
                //handleLocation(lastLocation);
            }
        }
        catch (SecurityException e){
            e.printStackTrace();
        }
    }

    private void handleLocation(Location location, String description){
        Log.d(TAG, "handleLocation called");
        //TextView view = (TextView) findViewById(R.id.coordinates);
        //view.setText(String.valueOf(location.getLatitude()) + ", " + String.valueOf(location.getLongitude()));
        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        Pin userLocation = new Pin(location, description, mapFragment, 18);
        userLocation.moveTheCamera();
    }


    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        if(connectionResult.hasResolution()){
            try{
                connectionResult.startResolutionForResult(this, CONNECTION_FAILURE_RESOLUTION_REQUEST);
            }
            catch(IntentSender.SendIntentException e){
                e.printStackTrace();
            }
        }
        else{
            Log.i(TAG, "Location services failed with error code:" + connectionResult.getErrorCode());
        }
    }
}

