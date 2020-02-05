
package com.yuplo.GoogleMap.MAp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GoogleDistanceApiResponse {

    @SerializedName("geocoded_waypoints")
    @Expose
    private List<GeocodedWaypoint> geocodedWaypoints = new ArrayList<GeocodedWaypoint>();
    @SerializedName("routes")
    @Expose
    private List<Route> routes = new ArrayList<Route>();
    @SerializedName("status")
    @Expose
    private String status;

    /**
     * 
     * @return
     *     The geocodedWaypoints
     */
    public List<GeocodedWaypoint> getGeocodedWaypoints() {
        return geocodedWaypoints;
    }

    /**
     * 
     * @param geocodedWaypoints
     *     The geocoded_waypoints
     */
    public void setGeocodedWaypoints(List<GeocodedWaypoint> geocodedWaypoints) {
        this.geocodedWaypoints = geocodedWaypoints;
    }

    /**
     * 
     * @return
     *     The routes
     */
    public List<Route> getRoutes() {
        return routes;
    }

    /**
     * 
     * @param routes
     *     The routes
     */
    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    /**
     * 
     * @return
     *     The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    public void setStatus(String status) {
        this.status = status;
    }



    public class Route {

        @SerializedName("bounds")
        @Expose
        private Bounds bounds;
        @SerializedName("copyrights")
        @Expose
        private String copyrights;
        @SerializedName("legs")
        @Expose
        private List<Leg> legs = new ArrayList<Leg>();
        @SerializedName("overview_polyline")
        @Expose
        private OverviewPolyline overviewPolyline;
        @SerializedName("summary")
        @Expose
        private String summary;
        @SerializedName("warnings")
        @Expose
        private List<Object> warnings = new ArrayList<Object>();
        @SerializedName("waypoint_order")
        @Expose
        private List<Object> waypointOrder = new ArrayList<Object>();

        /**
         *
         * @return
         *     The bounds
         */
        public Bounds getBounds() {
            return bounds;
        }

        /**
         *
         * @param bounds
         *     The bounds
         */
        public void setBounds(Bounds bounds) {
            this.bounds = bounds;
        }

        /**
         *
         * @return
         *     The copyrights
         */
        public String getCopyrights() {
            return copyrights;
        }

        /**
         *
         * @param copyrights
         *     The copyrights
         */
        public void setCopyrights(String copyrights) {
            this.copyrights = copyrights;
        }

        /**
         *
         * @return
         *     The legs
         */
        public List<Leg> getLegs() {
            return legs;
        }

        /**
         *
         * @param legs
         *     The legs
         */
        public void setLegs(List<Leg> legs) {
            this.legs = legs;
        }

        /**
         *
         * @return
         *     The overviewPolyline
         */
        public OverviewPolyline getOverviewPolyline() {
            return overviewPolyline;
        }

        /**
         *
         * @param overviewPolyline
         *     The overview_polyline
         */
        public void setOverviewPolyline(OverviewPolyline overviewPolyline) {
            this.overviewPolyline = overviewPolyline;
        }

        /**
         *
         * @return
         *     The summary
         */
        public String getSummary() {
            return summary;
        }

        /**
         *
         * @param summary
         *     The summary
         */
        public void setSummary(String summary) {
            this.summary = summary;
        }

        /**
         *
         * @return
         *     The warnings
         */
        public List<Object> getWarnings() {
            return warnings;
        }

        /**
         *
         * @param warnings
         *     The warnings
         */
        public void setWarnings(List<Object> warnings) {
            this.warnings = warnings;
        }

        /**
         *
         * @return
         *     The waypointOrder
         */
        public List<Object> getWaypointOrder() {
            return waypointOrder;
        }

        /**
         *
         * @param waypointOrder
         *     The waypoint_order
         */
        public void setWaypointOrder(List<Object> waypointOrder) {
            this.waypointOrder = waypointOrder;
        }

    }



    public class Leg {

        @SerializedName("distance")
        @Expose
        private Distance distance;
        @SerializedName("duration")
        @Expose
        private Duration duration;
        @SerializedName("end_address")
        @Expose
        private String endAddress;
        @SerializedName("end_location")
        @Expose
        private EndLocation endLocation;
        @SerializedName("start_address")
        @Expose
        private String startAddress;
        @SerializedName("start_location")
        @Expose
        private StartLocation startLocation;
        @SerializedName("steps")
        @Expose
        private List<Step> steps = new ArrayList<Step>();
        @SerializedName("via_waypoint")
        @Expose
        private List<Object> viaWaypoint = new ArrayList<Object>();

        /**
         *
         * @return
         *     The distance
         */
        public Distance getDistance() {
            return distance;
        }

        /**
         *
         * @param distance
         *     The distance
         */
        public void setDistance(Distance distance) {
            this.distance = distance;
        }

        /**
         *
         * @return
         *     The duration
         */
        public Duration getDuration() {
            return duration;
        }

        /**
         *
         * @param duration
         *     The duration
         */
        public void setDuration(Duration duration) {
            this.duration = duration;
        }

        /**
         *
         * @return
         *     The endAddress
         */
        public String getEndAddress() {
            return endAddress;
        }

        /**
         *
         * @param endAddress
         *     The end_address
         */
        public void setEndAddress(String endAddress) {
            this.endAddress = endAddress;
        }

        /**
         *
         * @return
         *     The endLocation
         */
        public EndLocation getEndLocation() {
            return endLocation;
        }

        /**
         *
         * @param endLocation
         *     The end_location
         */
        public void setEndLocation(EndLocation endLocation) {
            this.endLocation = endLocation;
        }

        /**
         *
         * @return
         *     The startAddress
         */
        public String getStartAddress() {
            return startAddress;
        }

        /**
         *
         * @param startAddress
         *     The start_address
         */
        public void setStartAddress(String startAddress) {
            this.startAddress = startAddress;
        }

        /**
         *
         * @return
         *     The startLocation
         */
        public StartLocation getStartLocation() {
            return startLocation;
        }

        /**
         *
         * @param startLocation
         *     The start_location
         */
        public void setStartLocation(StartLocation startLocation) {
            this.startLocation = startLocation;
        }

        /**
         *
         * @return
         *     The steps
         */
        public List<Step> getSteps() {
            return steps;
        }

        /**
         *
         * @param steps
         *     The steps
         */
        public void setSteps(List<Step> steps) {
            this.steps = steps;
        }

        /**
         *
         * @return
         *     The viaWaypoint
         */
        public List<Object> getViaWaypoint() {
            return viaWaypoint;
        }

        /**
         *
         * @param viaWaypoint
         *     The via_waypoint
         */
        public void setViaWaypoint(List<Object> viaWaypoint) {
            this.viaWaypoint = viaWaypoint;
        }

    }


    public class Step {

        @SerializedName("distance")
        @Expose
        private Distance_ distance;
        @SerializedName("duration")
        @Expose
        private Duration_ duration;
        @SerializedName("end_location")
        @Expose
        private EndLocation_ endLocation;
        @SerializedName("html_instructions")
        @Expose
        private String htmlInstructions;
        @SerializedName("polyline")
        @Expose
        private Polyline polyline;
        @SerializedName("start_location")
        @Expose
        private StartLocation_ startLocation;
        @SerializedName("travel_mode")
        @Expose
        private String travelMode;

        /**
         *
         * @return
         *     The distance
         */
        public Distance_ getDistance() {
            return distance;
        }

        /**
         *
         * @param distance
         *     The distance
         */
        public void setDistance(Distance_ distance) {
            this.distance = distance;
        }

        /**
         *
         * @return
         *     The duration
         */
        public Duration_ getDuration() {
            return duration;
        }

        /**
         *
         * @param duration
         *     The duration
         */
        public void setDuration(Duration_ duration) {
            this.duration = duration;
        }

        /**
         *
         * @return
         *     The endLocation
         */
        public EndLocation_ getEndLocation() {
            return endLocation;
        }

        /**
         *
         * @param endLocation
         *     The end_location
         */
        public void setEndLocation(EndLocation_ endLocation) {
            this.endLocation = endLocation;
        }

        /**
         *
         * @return
         *     The htmlInstructions
         */
        public String getHtmlInstructions() {
            return htmlInstructions;
        }

        /**
         *
         * @param htmlInstructions
         *     The html_instructions
         */
        public void setHtmlInstructions(String htmlInstructions) {
            this.htmlInstructions = htmlInstructions;
        }

        /**
         *
         * @return
         *     The polyline
         */
        public Polyline getPolyline() {
            return polyline;
        }

        /**
         *
         * @param polyline
         *     The polyline
         */
        public void setPolyline(Polyline polyline) {
            this.polyline = polyline;
        }

        /**
         *
         * @return
         *     The startLocation
         */
        public StartLocation_ getStartLocation() {
            return startLocation;
        }

        /**
         *
         * @param startLocation
         *     The start_location
         */
        public void setStartLocation(StartLocation_ startLocation) {
            this.startLocation = startLocation;
        }

        /**
         *
         * @return
         *     The travelMode
         */
        public String getTravelMode() {
            return travelMode;
        }

        /**
         *
         * @param travelMode
         *     The travel_mode
         */
        public void setTravelMode(String travelMode) {
            this.travelMode = travelMode;
        }

    }


    public class StartLocation_ {

        @SerializedName("lat")
        @Expose
        private float lat;
        @SerializedName("lng")
        @Expose
        private float lng;

        /**
         *
         * @return
         *     The lat
         */
        public float getLat() {
            return lat;
        }

        /**
         *
         * @param lat
         *     The lat
         */
        public void setLat(float lat) {
            this.lat = lat;
        }

        /**
         *
         * @return
         *     The lng
         */
        public float getLng() {
            return lng;
        }

        /**
         *
         * @param lng
         *     The lng
         */
        public void setLng(float lng) {
            this.lng = lng;
        }

    }


    public class StartLocation {

        @SerializedName("lat")
        @Expose
        private float lat;
        @SerializedName("lng")
        @Expose
        private float lng;

        /**
         *
         * @return
         *     The lat
         */
        public float getLat() {
            return lat;
        }

        /**
         *
         * @param lat
         *     The lat
         */
        public void setLat(float lat) {
            this.lat = lat;
        }

        /**
         *
         * @return
         *     The lng
         */
        public float getLng() {
            return lng;
        }

        /**
         *
         * @param lng
         *     The lng
         */
        public void setLng(float lng) {
            this.lng = lng;
        }

    }


    public class Southwest {

        @SerializedName("lat")
        @Expose
        private float lat;
        @SerializedName("lng")
        @Expose
        private float lng;

        /**
         *
         * @return
         *     The lat
         */
        public float getLat() {
            return lat;
        }

        /**
         *
         * @param lat
         *     The lat
         */
        public void setLat(float lat) {
            this.lat = lat;
        }

        /**
         *
         * @return
         *     The lng
         */
        public float getLng() {
            return lng;
        }

        /**
         *
         * @param lng
         *     The lng
         */
        public void setLng(float lng) {
            this.lng = lng;
        }

    }

    public class Polyline {

        @SerializedName("points")
        @Expose
        private String points;

        /**
         *
         * @return
         *     The points
         */
        public String getPoints() {
            return points;
        }

        /**
         *
         * @param points
         *     The points
         */
        public void setPoints(String points) {
            this.points = points;
        }

    }


    public class OverviewPolyline {

        @SerializedName("points")
        @Expose
        private String points;

        /**
         *
         * @return
         *     The points
         */
        public String getPoints() {
            return points;
        }

        /**
         *
         * @param points
         *     The points
         */
        public void setPoints(String points) {
            this.points = points;
        }

    }


    public class Northeast {

        @SerializedName("lat")
        @Expose
        private float lat;
        @SerializedName("lng")
        @Expose
        private float lng;

        /**
         *
         * @return
         *     The lat
         */
        public float getLat() {
            return lat;
        }

        /**
         *
         * @param lat
         *     The lat
         */
        public void setLat(float lat) {
            this.lat = lat;
        }

        /**
         *
         * @return
         *     The lng
         */
        public float getLng() {
            return lng;
        }

        /**
         *
         * @param lng
         *     The lng
         */
        public void setLng(float lng) {
            this.lng = lng;
        }

    }

    public class GeocodedWaypoint {

        @SerializedName("geocoder_status")
        @Expose
        private String geocoderStatus;
        @SerializedName("place_id")
        @Expose
        private String placeId;
        @SerializedName("types")
        @Expose
        private List<String> types = new ArrayList<String>();

        /**
         *
         * @return
         *     The geocoderStatus
         */
        public String getGeocoderStatus() {
            return geocoderStatus;
        }

        /**
         *
         * @param geocoderStatus
         *     The geocoder_status
         */
        public void setGeocoderStatus(String geocoderStatus) {
            this.geocoderStatus = geocoderStatus;
        }

        /**
         *
         * @return
         *     The placeId
         */
        public String getPlaceId() {
            return placeId;
        }

        /**
         *
         * @param placeId
         *     The place_id
         */
        public void setPlaceId(String placeId) {
            this.placeId = placeId;
        }

        /**
         *
         * @return
         *     The types
         */
        public List<String> getTypes() {
            return types;
        }

        /**
         *
         * @param types
         *     The types
         */
        public void setTypes(List<String> types) {
            this.types = types;
        }

    }


    public class EndLocation_ {

        @SerializedName("lat")
        @Expose
        private float lat;
        @SerializedName("lng")
        @Expose
        private float lng;

        /**
         *
         * @return
         *     The lat
         */
        public float getLat() {
            return lat;
        }

        /**
         *
         * @param lat
         *     The lat
         */
        public void setLat(float lat) {
            this.lat = lat;
        }

        /**
         *
         * @return
         *     The lng
         */
        public float getLng() {
            return lng;
        }

        /**
         *
         * @param lng
         *     The lng
         */
        public void setLng(float lng) {
            this.lng = lng;
        }

    }


    public class EndLocation {

        @SerializedName("lat")
        @Expose
        private float lat;
        @SerializedName("lng")
        @Expose
        private float lng;

        /**
         *
         * @return
         *     The lat
         */
        public float getLat() {
            return lat;
        }

        /**
         *
         * @param lat
         *     The lat
         */
        public void setLat(float lat) {
            this.lat = lat;
        }

        /**
         *
         * @return
         *     The lng
         */
        public float getLng() {
            return lng;
        }

        /**
         *
         * @param lng
         *     The lng
         */
        public void setLng(float lng) {
            this.lng = lng;
        }

    }

    public class Duration_ {

        @SerializedName("text")
        @Expose
        private String text;
        @SerializedName("value")
        @Expose
        private int value;

        /**
         *
         * @return
         *     The text
         */
        public String getText() {
            return text;
        }

        /**
         *
         * @param text
         *     The text
         */
        public void setText(String text) {
            this.text = text;
        }

        /**
         *
         * @return
         *     The value
         */
        public int getValue() {
            return value;
        }

        /**
         *
         * @param value
         *     The value
         */
        public void setValue(int value) {
            this.value = value;
        }

    }

    public class Duration {

        @SerializedName("text")
        @Expose
        private String text;
        @SerializedName("value")
        @Expose
        private int value;

        /**
         *
         * @return
         *     The text
         */
        public String getText() {
            return text;
        }

        /**
         *
         * @param text
         *     The text
         */
        public void setText(String text) {
            this.text = text;
        }

        /**
         *
         * @return
         *     The value
         */
        public int getValue() {
            return value;
        }

        /**
         *
         * @param value
         *     The value
         */
        public void setValue(int value) {
            this.value = value;
        }

    }


    public class Distance_ {

        @SerializedName("text")
        @Expose
        private String text;
        @SerializedName("value")
        @Expose
        private int value;

        /**
         *
         * @return
         *     The text
         */
        public String getText() {
            return text;
        }

        /**
         *
         * @param text
         *     The text
         */
        public void setText(String text) {
            this.text = text;
        }

        /**
         *
         * @return
         *     The value
         */
        public int getValue() {
            return value;
        }

        /**
         *
         * @param value
         *     The value
         */
        public void setValue(int value) {
            this.value = value;
        }

    }

    public class Distance {

        @SerializedName("text")
        @Expose
        private String text;
        @SerializedName("value")
        @Expose
        private int value;

        /**
         *
         * @return
         *     The text
         */
        public String getText() {
            return text;
        }

        /**
         *
         * @param text
         *     The text
         */
        public void setText(String text) {
            this.text = text;
        }

        /**
         *
         * @return
         *     The value
         */
        public int getValue() {
            return value;
        }

        /**
         *
         * @param value
         *     The value
         */
        public void setValue(int value) {
            this.value = value;
        }

    }

    public class Bounds {

        @SerializedName("northeast")
        @Expose
        private Northeast northeast;
        @SerializedName("southwest")
        @Expose
        private Southwest southwest;

        /**
         *
         * @return
         *     The northeast
         */
        public Northeast getNortheast() {
            return northeast;
        }

        /**
         *
         * @param northeast
         *     The northeast
         */
        public void setNortheast(Northeast northeast) {
            this.northeast = northeast;
        }

        /**
         *
         * @return
         *     The southwest
         */
        public Southwest getSouthwest() {
            return southwest;
        }

        /**
         *
         * @param southwest
         *     The southwest
         */
        public void setSouthwest(Southwest southwest) {
            this.southwest = southwest;
        }

    }


}
