package com.example.joshhan.dinesafe;

import java.util.ArrayList;

/**
 * Created by panda on 2017-06-04.
 */

public class Restaurant {
    private int establishmentID;
    private String name;
    private String type;
    private String address;
    private String status;
    private int minimumInspectionsPerYear;
    private double longitude;
    private double latitude;
    private ArrayList<UsefulReview> reviews;

    public Restaurant(int establishmentID, String name, String type, String address, String status,
                      int minimumInspectionsPerYear, double longitude, double latitude, ArrayList<UsefulReview> reviews) {
        super();
        this.establishmentID = establishmentID;
        this.name = name;
        this.type = type;
        this.address = address;
        this.status = status;
        this.minimumInspectionsPerYear = minimumInspectionsPerYear;
        this.longitude = longitude;
        this.latitude = latitude;
        this.reviews = reviews;
    }


    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getAddress() {
        return address;
    }

    public String getStatus() {
        return status;
    }

    public int getMinimumInspectionsPerYear() {
        return minimumInspectionsPerYear;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public ArrayList<UsefulReview> getReviews() {
        return reviews;
    }

    public String forSearch() {
        return name +
                "\n" + address;
    }

    public int getEstablishmentID() {
        return establishmentID;
    }
}


