package com.example.roomadsifull.controller

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.roomadsifull.models.Gender

@Dao
interface GenderDAO {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(gender: Gender) : Long
}