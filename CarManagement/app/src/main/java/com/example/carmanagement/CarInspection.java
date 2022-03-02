package com.example.carmanagement;

public class CarInspection {
    private String name;
    private String brand;
    private String model;
    private String purchaseDate;
    private int price;
    private String motorType;
    private String transmissionType;
    private int kilometers;
    private int idCar;
    private int id;
    private int idInspection;
    private String serviceName;
    private String date;
    private float priceInspection;

    public CarInspection(String name, String brand, String model, String purchaseDate, int price, String motorType, String transmissionType, int kilometers, int idCar, int id, int idInspection, String serviceName, String date, float priceInspection) {
        this.name = name;
        this.brand = brand;
        this.model = model;
        this.purchaseDate = purchaseDate;
        this.price = price;
        this.motorType = motorType;
        this.transmissionType = transmissionType;
        this.kilometers = kilometers;
        this.idCar = idCar;
        this.id=id;
        this.idInspection = idInspection;
        this.serviceName = serviceName;
        this.date = date;
        this.priceInspection = priceInspection;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getMotorType() {
        return motorType;
    }

    public void setMotorType(String motorType) {
        this.motorType = motorType;
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    public int getKilometers() {
        return kilometers;
    }

    public void setKilometers(int kilometers) {
        this.kilometers = kilometers;
    }

    public int getIdCar() {
        return idCar;
    }

    public void setIdCar(int idCar) {
        this.idCar = idCar;
    }

    public int getIdInspection() {
        return idInspection;
    }

    public void setIdInspection(int idInspection) {
        this.idInspection = idInspection;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getPriceInspection() {
        return priceInspection;
    }

    public void setPriceInspection(float priceInspection) {
        this.priceInspection = priceInspection;
    }

    @Override
    public String toString() {
        return "CarInspection{" +
                "name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", purchaseDate='" + purchaseDate + '\'' +
                ", price=" + price +
                ", motorType='" + motorType + '\'' +
                ", transmissionType='" + transmissionType + '\'' +
                ", kilometers=" + kilometers +
                ", idCar=" + idCar +
                ", idInspection=" + idInspection +
                ", serviceName='" + serviceName + '\'' +
                ", date='" + date + '\'' +
                ", priceInspection=" + priceInspection +
                '}';
    }
}
