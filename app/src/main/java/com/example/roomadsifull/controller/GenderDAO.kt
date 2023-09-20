package com.example.roomadsifull.controller

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.roomadsifull.models.Gender

@Dao
interface GenderDAO {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(gender: Gender): Long

    @Query("SELECT * FROM ${Gender.TABLE_NAME}")
    fun getAll():List<Gender>
    @Delete
    fun delete(gender: Gender)

    @Update
    fun update(gender: Gender)
}