package com.fastporte.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fastporte.models.Driver

@Database(entities=[Driver::class], version=1)
abstract class PopularDriverDB : RoomDatabase() {
    abstract fun getPopularDriverDAO():PopularDriverDao
    companion object {
        private var INSTANCE: PopularDriverDB?=null
        fun getInstance(context: Context):PopularDriverDB{
            if(INSTANCE==null){
                INSTANCE= Room
                    .databaseBuilder(context, PopularDriverDB::class.java, "popular_driver.dbb")
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE as PopularDriverDB
        }
    }
}