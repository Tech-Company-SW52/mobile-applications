package com.fastporte.database

import androidx.room.*
import com.fastporte.models.Driver
import com.fastporte.models.PopularDriver

@Dao
interface PopularDriverDao {
    @Insert
    fun insertPopularDriver(vararg popularDriver: Driver)

    @Query("SELECT * FROM popular_drivers")
    fun getAllPopularDrivers(): List<Driver>

    @Delete
    fun deletePopularDriver(vararg popularDriver: Driver)

    @Update
    fun updatePopularDriver(vararg popularDriver: Driver)

    //Verificar si un popular driver existe y devolver un booleano
    @Query("SELECT EXISTS(SELECT * FROM popular_drivers WHERE id = :id)")
    fun existsPopularDriver(id: Int): Boolean
}