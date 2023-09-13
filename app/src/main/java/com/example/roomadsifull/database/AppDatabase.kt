package com.example.roomadsifull.database

import android.content.ContentValues
import android.content.Context
import androidx.room.Database
import androidx.room.OnConflictStrategy
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.roomadsifull.controller.GenderDAO
import com.example.roomadsifull.models.Gender

@Database(entities = [Gender::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {

    abstract fun  genderDAO():GenderDAO
    companion object {
        const val DATABASE_NAME = "db_test"

        @Volatile
        private var INSTANCIA: AppDatabase? = null
        fun GetDataBase(context: Context): AppDatabase {
            if (INSTANCIA != null) {
                return INSTANCIA!!
            }

            INSTANCIA = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                DATABASE_NAME
            ).addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    var list = listOf(
                        Gender(0, "Femenino"),
                        Gender(0, "Masculino")
                    )
                    list.forEach {
                        gender ->
                        db.insert(Gender.TABLE_NAME, OnConflictStrategy.ABORT, ContentValues().apply {
                            put(Gender.COL_NAME, gender.name)
                        })
                    }
                }
            }).build()
            return INSTANCIA!!

        }

    }
}