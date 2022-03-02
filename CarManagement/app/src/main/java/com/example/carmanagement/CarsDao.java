package com.example.carmanagement;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CarsDao {

    @Insert
    void insert(Car car);

    @Insert
    void insert(List<Car> carList);

    @Query("select * from cars")
    List<Car> getAll();

    @Query("delete from cars")
    void deleteAll();

    @Delete
    void delete(Car car);
}
