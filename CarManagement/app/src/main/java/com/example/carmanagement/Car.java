package com.example.carmanagement;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "cars", foreignKeys = @ForeignKey(entity = TechInspection.class, parentColumns = "id", childColumns = "idInspection", onDelete = CASCADE), indices = @Index("idInspection"))
public class Car implements Serializable {

    private String name;
    private String brand;
    private String model;
    private String purchaseDate;
    private int price;
    private String motorType;
    private String transmissionType;
    private int kilometers;

    @PrimaryKey(autoGenerate = true)
    private int idCar;

    public int getIdCar() {
        return idCar;
    }

    public void setIdCar(int idCar) {
        this.idCar = idCar;
    }

    private int idInspection;

    public int getIdInspection() {
        return idInspection;
    }

    public void setIdInspection(int idInspection) {
        this.idInspection = idInspection;
    }
@Ignore
        private String uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Ignore
    public Car() {

    }

    @Ignore
    public Car(String name, String brand, String model, String purchaseDate, int price, String motorType, String transmissionType, int kilometers) {
        this.name = name;
        this.brand = brand;
        this.model = model;
        this.purchaseDate = purchaseDate;
        this.price = price;
        this.motorType = motorType;
        this.transmissionType = transmissionType;
        this.kilometers = kilometers;
    }

    public Car(String name, String brand, String model, String purchaseDate, int price, String motorType, String transmissionType, int kilometers, int idInspection) {
        this.name = name;
        this.brand = brand;
        this.model = model;
        this.purchaseDate = purchaseDate;
        this.price = price;
        this.motorType = motorType;
        this.transmissionType = transmissionType;
        this.kilometers = kilometers;
        this.idInspection = idInspection;
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

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", purchaseDate=" + purchaseDate +
                ", price=" + price +
                ", motorType='" + motorType + '\'' +
                ", transmissionType='" + transmissionType + '\'' +
                ", kilometers=" + kilometers +
                '}';
    }
}