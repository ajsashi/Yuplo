package com.yuplo.support;

import android.location.Location;

import java.util.HashMap;
import java.util.List;

public interface MapListener {
    void getLocation(Location location);

    void onRouteObtained(List<List<HashMap<String, String>>> routes);
}

