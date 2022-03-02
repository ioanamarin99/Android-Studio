package com.example.carmanagement;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CarsInspectionsDao {
    @Query("select * from cars join inspections on cars.idInspection = inspections.id")
    List<CarInspection> getCarsWithInspections();
}
