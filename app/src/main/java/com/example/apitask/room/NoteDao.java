package com.example.apitask.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.apitask.models.ParcelDelivery;

import java.util.List;

@Dao
public interface NoteDao {

    // to view data

    @Query("SELECT * FROM parcel_delievry order by delievry_time desc")
    LiveData<List<ParcelDelivery>> getAllNotesLive();

    @Query("SELECT EXISTS(SELECT * FROM parcel_delievry WHERE qrCode = :parcel_no)")
    Boolean is_exists(String parcel_no);

    @Query("SELECT * FROM parcel_delievry order by delievry_time desc")
    List<ParcelDelivery> getAllNotes();


    @Insert
    void insertNotes(ParcelDelivery...notes);

    @Update
    void updateNotes(ParcelDelivery...notes);

    @Delete
    void deleteNotes(ParcelDelivery note);
}
