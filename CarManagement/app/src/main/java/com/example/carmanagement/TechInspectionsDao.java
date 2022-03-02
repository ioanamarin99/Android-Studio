package com.example.carmanagement;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TechInspectionsDao {

    @Insert
    void insert(TechInspection inspection);

    @Query("select * from inspections")
    List<TechInspection> getAll();

    @Query("delete from inspections")
    void deleteAll();
}
