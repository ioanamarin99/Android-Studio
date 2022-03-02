package com.example.carmanagement;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "inspections")
public class TechInspection {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String serviceName;
    private String date;
    private float priceInspection;

    public TechInspection(int id, String serviceName, String date, float priceInspection) {
        this.id = id;
        this.serviceName = serviceName;
        this.date = date;
        this.priceInspection = priceInspection;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return "TechInspection{" +
                "id=" + id +
                ", serviceName='" + serviceName + '\'' +
                ", date='" + date + '\'' +
                ", priceInspection=" + priceInspection +
                '}';
    }
}
