package com.yuplo.view.fragment;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.RoundCap;
import com.yuplo.GoogleMap.GetRoute;
import com.yuplo.R;
import com.yuplo.base.BaseFragment;
import com.yuplo.support.Constants;
import com.yuplo.support.LocationSupportManager;
import com.yuplo.support.MapListener;
import com.yuplo.support.MyPreferenceManager;
import com.yuplo.support.Utils;
import com.yuplo.support.fragmentmanager.manager.IFragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;


public class NewScheduleScreenFragment extends BaseFragment implements IFragment,
        OnMapReadyCallback,
        MapListener,GoogleMap.OnPolylineClickListener,
        GoogleMap.OnPolygonClickListener{

    @Inject
    Utils utils;
    private GoogleMap mMap;
    @Inject
    MyPreferenceManager preferenceManager;

    @BindView(R.id.txt_location)
    TextView txtLocation;
    /*
        FusedLocationProviderClient fusedLocationProviderClient;
        private LocationRequest mLocationRequest;
        private static int UPDATE_INTERVAL = 5000;
        private static int FASTEST_INTERVAL = 3000;
        private static int DISPLACEMENT = 10;
        private Location mLastLocation;
        LocationCallback locationCallback;*/
    private Boolean notFirst;
    private ArrayList points;
    private PolylineOptions polylineOptions;
    private ArrayList<LatLng> wayPoint;


    public static IFragment newInstance() {
        return new NewScheduleScreenFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_new_schedule_screen;
    }

    @Override
    public String getFragmentName() {
        return "New ScheduleScreenFragment";
    }

    @Override
    public void setTitle() {
        yuploFragmentChannel.setToolbarTitle("New Schedule");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injector().inject(this);
        Boolean prefData = preferenceManager.getBoolsData(Constants.getLocationPermission());
        notFirst = prefData;

//        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(Objects.requireNonNull(getActivity()));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        yuploFragmentChannel.setToolbarTitle("New Schedule");
        SupportMapFragment mMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mMapFragment.getMapAsync(this);
    }

    @OnClick(R.id.btn_card_save)
    public void proceed() {
        Toast.makeText(getContext(), "Address Saved", Toast.LENGTH_SHORT).show();
        yuploFragmentChannel.showFinalSchedule();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }

    private void askLocationPermission() {
        if (ActivityCompat.checkSelfPermission(getContext(),
                ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(),
                ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) getContext(), ACCESS_FINE_LOCATION)) {

                ActivityCompat.requestPermissions((Activity) getContext(), new String[]{ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION}, Constants.getLocationRequestCode());
            } else {
                if (!notFirst) {
                    ActivityCompat.requestPermissions((Activity) getContext(), new String[]{ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION}, Constants.getLocationRequestCode());


                    preferenceManager.storeBoolsData(Constants.getLocationPermission(), false);
                    notFirst = true;
                } else {
                    Toast.makeText(getContext(), getResources().getString(R.string.enable_location), Toast.LENGTH_SHORT).show();

                }
            }
        } else {
            Toast.makeText(getContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
        }

    }

    private void updateScreen() {
        boolean fineLocation = utils.hasPermission(getContext(), ACCESS_FINE_LOCATION);
        boolean coarseLocation = utils.hasPermission(getContext(), ACCESS_COARSE_LOCATION);
        if (fineLocation && coarseLocation) {

            LocationSupportManager manager = new LocationSupportManager(getContext(), this);
            manager.buildLocationRequest();
            manager.buildLocationCallBacks();
            manager.displayLocation();
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        if (utils.isLocationEnabled(getContext())) {
            if (ActivityCompat.checkSelfPermission(getActivity(),
                    ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(),
                    ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                updateScreen();
            } else {
                askLocationPermission();
            }
        } else {
            utils.showlocationAlert(getContext());
        }
    }

    @Override
    public void getLocation(Location location) {

        LatLng currentlocation = new LatLng(location.getLatitude(), location.getLongitude());
        mMap.addMarker(new MarkerOptions().position(currentlocation).title("Your Location").icon(BitmapDescriptorFactory.fromBitmap(utils.getBluePinWithNumber(getContext(),0))));
        LatLng balajiGarden = new LatLng(11.069768, 76.931569);
        mMap.addMarker(new MarkerOptions().position(balajiGarden).title("balajiGarden").icon(BitmapDescriptorFactory.fromBitmap(utils.getBluePinWithNumber(getContext(),1))));
        LatLng ganeshNagar = new LatLng(11.057529, 76.942162);
        mMap.addMarker(new MarkerOptions().position(ganeshNagar).title("ganeshNagar").icon(BitmapDescriptorFactory.fromBitmap(utils.getBluePinWithNumber(getContext(),2))));

        LatLng destinationLocation = new LatLng(11.06038, 76.94568);
        mMap.addMarker(new MarkerOptions().position(destinationLocation).title("Destination").icon(BitmapDescriptorFactory.fromBitmap(utils.getBluePinWithNumber(getContext(),3))));

        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(currentlocation);
        builder.include(balajiGarden);
        builder.include(ganeshNagar);
        builder.include(destinationLocation);
        LatLngBounds bounds = builder.build();
        int padding = 100; // offset from edges of the map in pixels
        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
        mMap.moveCamera(cu);
        mMap.animateCamera(cu);

   /*    mMap.addPolyline(new PolylineOptions().clickable(true).color(R.color.blue)
                .add(currentlocation,balajiGarden,ganeshNagar));

        mMap.setOnPolylineClickListener(this);
        mMap.setOnPolygonClickListener(this);*/
        Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());

        wayPoint = new ArrayList<>();
        wayPoint.add(balajiGarden);
        wayPoint.add(ganeshNagar);

        drawDirection(currentlocation,destinationLocation,wayPoint);
//        drawDirection(balajiGarden,ganeshNagar);
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            String local_address = String.valueOf(addresses.get(0).getAddressLine(0));
            txtLocation.setText(local_address);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRouteObtained(List<List<HashMap<String, String>>> routes) {
        Log.d("",routes.size()+"");
        for (int i = 0; i < routes.size(); i++) {

            points = new ArrayList();
            polylineOptions = new PolylineOptions();

            List<HashMap<String, String>> path = routes.get(i);

            for (int j = 0; j < path.size(); j++) {
                HashMap<String, String> point = path.get(j);
                double lat = Double.parseDouble(point.get("lat"));
                double lng = Double.parseDouble(point.get("lng"));
                LatLng position = new LatLng(lat, lng);
                points.add(position);
            }
            polylineOptions.addAll(points);
            polylineOptions.width(10);
            polylineOptions.color(Color.rgb(91, 59, 1));
            polylineOptions.geodesic(true);
        }

        if (polylineOptions != null){
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mMap.addPolyline(polylineOptions);
                }
            });
             }
    }

    private synchronized void drawDirection(LatLng latLng, LatLng staticLocation, ArrayList<LatLng> waypoint) {
        GetRoute route = new GetRoute();
        route.getDirection(getContext(),latLng,staticLocation,waypoint,this);
    }

    @Override
    public void onPolygonClick(Polygon polygon) {
        Toast.makeText(getContext(), "Route type ",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPolylineClick(Polyline polyline) {
/*        Toast.makeText(getContext(), "Route type " + polyline.getTag().toString(),
                Toast.LENGTH_SHORT).show();*/
    }

}
