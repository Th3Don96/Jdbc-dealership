package com.pluralsight;

public class Vehicle {
    //Vehicle attributes
    private String make, model, color, vehicleType;
    private String vin, year, odometer;
    private double price;

    public Vehicle(String vin, String year, String make, String model, String color, String odometer, double price) {
        this.vin = vin;
        this.year = year;
        this.make = make;
        this.model = model;
        this.color = color;
        this.odometer = odometer;
        this.price = price;

    }

    @Override
    public String toString() {
        return "Vehicle: " +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", vin=" + vin +
                ", year=" + year +
                ", odometer=" + odometer +
                ", price=" + price +
                '}';
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getOdometer() {
        return odometer;
    }

    public void setOdometer(String odometer) {
        this.odometer = odometer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}