package com.example.roomadsifull.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = Gender.TABLE_NAME,
    indices = [Index(value = [Gender.COL_NAME], unique = true)])
data class Gender(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COL_ID )
    var id : Long,
    @ColumnInfo(name = COL_NAME)
    var name : String) {

    override fun toString(): String {
        return "$name"
    }

    companion object {
       const val TABLE_NAME = "gender"
       const val COL_ID = "id"
       const val COL_NAME = "name"
    }
}


/*

CREATE TABLE Gender //@Entity(tableName = Gender.TABLE_NAME)
(
id INTEGER  NOT NULL PRIMARY KEY AUTO_INCREMENT //@PrimaryKey(autoGenerate = true)
name TEXT NOT NULL UNIQUE //     @ColumnInfo(name = COL_ID )
)
 */