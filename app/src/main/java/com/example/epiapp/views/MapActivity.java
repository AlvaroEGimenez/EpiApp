package com.example.epiapp.views;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.epiapp.GpsUtils;
import com.example.epiapp.House;
import com.example.epiapp.HouseAdapter;
import com.example.epiapp.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MapActivity extends FragmentActivity implements GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener,
        OnMapReadyCallback {

    private boolean isGPS;
    private GoogleMap mMap;
    private LatLng latLngMarker;
    private List<House> houseList = new ArrayList<>();


    @BindView(R.id.add_button)
    Button buttonAdd;
    @BindView(R.id.recyclerview_house)
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        ButterKnife.bind(this);


        FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, location -> {
                    // Got last known location. In some rare situations this can be null.
                    if (location != null) {
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 13));

                        CameraPosition cameraPosition = new CameraPosition.Builder()
                                .target(new LatLng(location.getLatitude(), location.getLongitude()))      // Sets the center of the map to location user
                                .zoom(90)                   // Sets the zoom
                                .bearing(90)                // Sets the orientation of the camera to east
                                .build();                   // Creates a CameraPosition from the builder
                        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                    }
                });


        new GpsUtils(this).turnGPSOn(isGPSEnable -> {
            // turn on GPS
            isGPS = isGPSEnable;
        });


        buttonAdd.setOnClickListener(view -> {
            if (latLngMarker != null) {
                Intent intent = new Intent(MapActivity.this, DataActivity.class);
                Bundle bundle = new Bundle();
                bundle.putDouble(DataActivity.KEY_LAT, latLngMarker.latitude);
                bundle.putDouble(DataActivity.KEY_LONG, latLngMarker.longitude);
                intent.putExtras(bundle);
                startActivity(intent);
            }

        });

        houseList = getHouseList();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        HouseAdapter houseAdapter = new HouseAdapter(houseList);
        recyclerView.setAdapter(houseAdapter);


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 1000) {
                isGPS = true; // flag maintain before get location
            }
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        googleMap.setMyLocationEnabled(true);
        googleMap.setOnMyLocationButtonClickListener(this);
        googleMap.setOnMyLocationClickListener(this);

        googleMap.setOnMapClickListener(latLng -> {
            // Creating a marker
            MarkerOptions markerOptions = new MarkerOptions();

            // Setting the position for the marker
            markerOptions.position(latLng);

            // Setting the title for the marker.
            // This will be displayed on taping the marker
            markerOptions.title(latLng.latitude + " : " + latLng.longitude);

            // Clears the previously touched position
            googleMap.clear();

            // Animating to the touched position
            googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

            // Placing a marker on the touched position
            googleMap.addMarker(markerOptions);

            latLngMarker = latLng;

            buttonAdd.setVisibility(View.VISIBLE);
        });

        for (int i = 0; i < houseList.size(); i++) {

            LatLng position = new LatLng(houseList.get(i).getLatitude(), houseList.get(i).getLongitude());
            mMap.addMarker(new MarkerOptions().position(position));
        }


    }

    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        Toast.makeText(this, "Current location:\n" + location, Toast.LENGTH_LONG).show();
    }

    private List<House> getHouseList() {
        House house = new House(1, -27.0454352, -55.2252587, "Centro", "Jardin America", 1, 374345124, "Araujo", "Casa rejas negras");
        House house1 = new House(1, -27.0464364, -55.2252690, "Centro", "Jardin America", 1, 374345124, "Araujo", "Casa rejas negras");
        House house2 = new House(2, -27.0474376, -55.2253793, "Centro", "Jardin America", 1, 374345124, "Araujo", "Casa rejas negras");
        House house3 = new House(5, -27.0484388, -55.2254896, "Centro", "Jardin America", 1, 374345124, "Araujo", "Casa rejas negras");
        House house4 = new House(3, -27.0494390, -55.2255999, "Centro", "Jardin America", 1, 374345124, "Araujo", "Casa rejas negras");
        House house5 = new House(4, -27.0404362, -55.2256601, "Centro", "Jardin America", 1, 374345124, "Araujo", "Casa rejas negras");

        houseList.add(house);
        houseList.add(house1);
        houseList.add(house2);
        houseList.add(house3);
        houseList.add(house4);
        houseList.add(house5);

        return houseList;
    }


}
