
package com.yuplo.GoogleMap.MAp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class DistanceMatrixResponse {

    @SerializedName("destination_addresses")
    @Expose
    private List<String> destinationAddresses = new ArrayList<String>();
    @SerializedName("origin_addresses")
    @Expose
    private List<String> originAddresses = new ArrayList<String>();
    @SerializedName("rows")
    @Expose
    private List<Row> rows = new ArrayList<Row>();
    @SerializedName("status")
    @Expose
    private String status;

    /**
     * 
     * @return
     *     The destinationAddresses
     */
    public List<String> getDestinationAddresses() {
        return destinationAddresses;
    }

    /**
     * 
     * @param destinationAddresses
     *     The destination_addresses
     */
    public void setDestinationAddresses(List<String> destinationAddresses) {
        this.destinationAddresses = destinationAddresses;
    }

    /**
     * 
     * @return
     *     The originAddresses
     */
    public List<String> getOriginAddresses() {
        return originAddresses;
    }

    /**
     * 
     * @param originAddresses
     *     The origin_addresses
     */
    public void setOriginAddresses(List<String> originAddresses) {
        this.originAddresses = originAddresses;
    }

    /**
     * 
     * @return
     *     The rows
     */
    public List<Row> getRows() {
        return rows;
    }

    /**
     * 
     * @param rows
     *     The rows
     */
    public void setRows(List<Row> rows) {
        this.rows = rows;
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


    public class Row {

        @SerializedName("elements")
        @Expose
        private List<Element> elements = new ArrayList<Element>();

        /**
         *
         * @return
         *     The elements
         */
        public List<Element> getElements() {
            return elements;
        }

        /**
         *
         * @param elements
         *     The elements
         */
        public void setElements(List<Element> elements) {
            this.elements = elements;
        }

    }

    public class Element {

        @SerializedName("distance")
        @Expose
        private Distance distance;
        @SerializedName("duration")
        @Expose
        private Duration duration;
        @SerializedName("status")
        @Expose
        private String status;

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

}
