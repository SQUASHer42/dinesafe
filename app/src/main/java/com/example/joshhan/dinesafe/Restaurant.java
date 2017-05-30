package com.example.joshhan.dinesafe;

public class Restaurant {
    private int id;
    private String name;
    private String type;
    private String address;
    private String status;
    private int minimuminspectionsperyear;

    public void setAddress(String address) {
        this.address = address;
    }

    public Restaurant(int a, String b, String c, String d, String e, int f) {
        id = a;
        name = b;
        type = c;
        address = d;
        status = e;
        minimuminspectionsperyear = f;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMinimuminspectionsperyear() {
        return minimuminspectionsperyear;
    }

    public void setMinimuminspectionsperyear(int minimuminspectionsperyear) {
        this.minimuminspectionsperyear = minimuminspectionsperyear;
    }



    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", address='" + address + '\'' +
                ", status='" + status + '\'' +
                ", minimuminspectionsperyear=" + minimuminspectionsperyear +
                '}';
    }
}