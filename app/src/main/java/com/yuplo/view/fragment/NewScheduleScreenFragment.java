package com.yuplo.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.yuplo.R;
import com.yuplo.base.BaseFragment;
import com.yuplo.support.Constants;
import com.yuplo.support.MyPreferenceManager;
import com.yuplo.support.Utils;
import com.yuplo.support.fragmentmanager.manager.IFragment;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;


public class NewScheduleScreenFragment extends BaseFragment implements IFragment, OnMapReadyCallback {

    @Inject
    Utils utils;
    private GoogleMap mMap;
    @Inject
    MyPreferenceManager preferenceManager;

    @BindView(R.id.txt_location)
    TextView txtLocation;

    FusedLocationProviderClient fusedLocationProviderClient;
    private LocationRequest mLocationRequest;
    private static int UPDATE_INTERVAL = 5000;
    private static int FASTEST_INTERVAL = 3000;
    private static int DISPLACEMENT = 10;
    private Location mLastLocation;
    LocationCallback locationCallback;
    private Boolean notFirst;


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

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(Objects.requireNonNull(getActivity()));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        yuploFragmentChannel.setToolbarTitle("New Schedule");
        SupportMapFragment mMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mMapFragment.getMapAsync(this);
    }

    @OnClick(R.id.btn_card_save)
    public void proceed(){
        Toast.makeText(getContext(), "Address Saved", Toast.LENGTH_SHORT).show();
        yuploFragmentChannel.showFinalSchedule();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng TutorialsPoint = new LatLng(21, 57);

    }

    public void askLocationPermission(Context context, Activity activity) {
        if (ActivityCompat.checkSelfPermission(activity,
                ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(activity,
                ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, ACCESS_FINE_LOCATION)) {

                ActivityCompat.requestPermissions((Activity) context, new String[]{ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION}, Constants.getLocationRequestCode());
            } else {
                if (!notFirst) {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION}, Constants.getLocationRequestCode());


                    preferenceManager.storeBoolsData(Constants.getLocationPermission(), false);
                    notFirst = true;
                } else {
                    Toast.makeText(context, getResources().getString(R.string.enable_location), Toast.LENGTH_SHORT).show();

                    /*Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                            Uri.parse("package:" + activity.getPackageName()));
                    intent.addCategory(Intent.CATEGORY_DEFAULT);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    activity.startActivity(intent);*/
                }
            }
        } else {
            Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show();
        }

    }

    private void updateScreen() {
        boolean fineLocation = utils.hasPermission(getContext(), ACCESS_FINE_LOCATION);
        boolean coarseLocation = utils.hasPermission(getContext(), ACCESS_COARSE_LOCATION);
        if (fineLocation && coarseLocation) {
            buildLocationRequest();
            buildLocationCallBacks();
            displayLocation();
        }
    }

    private void buildLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(UPDATE_INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setSmallestDisplacement(DISPLACEMENT);
    }

    private void buildLocationCallBacks() {
        Log.d("Location update", "buildLocationCallBacks called");
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                for (Location location : locationResult.getLocations()) {
                    mLastLocation = location;
                    Log.d("Location update", String.valueOf(mLastLocation));
                    if (mLastLocation != null) {
                        Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
                        List<Address> addresses = null;
                        try {
                            addresses = geocoder.getFromLocation(mLastLocation.getLatitude(), mLastLocation.getLongitude(), 1);
                            Toast.makeText(getActivity(), "callback" + addresses.get(0).getCountryName(), Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }
                displayLocation();
            }
        };
    }

    private void displayLocation() {
        if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fusedLocationProviderClient.getLastLocation()
                .addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {

                        mLastLocation = location;
                        if (mLastLocation != null) {
                            LatLng latLng = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
                            mMap.addMarker(new
                                    MarkerOptions().position(latLng).title("Your Location"));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16f));
                            Log.d("Location update", (mLastLocation.getLatitude()) + "," + (mLastLocation.getLongitude()));
                            Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
                            List<Address> addresses = null;
                            try {
                                addresses = geocoder.getFromLocation(mLastLocation.getLatitude(), mLastLocation.getLongitude(), 1);
                                String local_address = String.valueOf(addresses.get(0).getAddressLine(0));
                                txtLocation.setText(local_address);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            if (addresses != null) {
                                String countryName = addresses.get(0).getCountryName();
                                Log.d("Google Maps Location", countryName);
                            }

                        } else {
                            fusedLocationProviderClient.requestLocationUpdates(mLocationRequest, locationCallback, Looper.myLooper());
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
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
                askLocationPermission(getContext(), getActivity());
            }
        } else {
            utils.showlocationAlert(getContext());
        }
    }
}
