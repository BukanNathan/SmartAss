package com.example.converterapp2

import androidx.room.*

@Dao
interface RoomUserDAO {
    @Query("SELECT * FROM ROOMUSER")
    fun getAllData() : List<RoomUser>

    @Insert
    fun insertAll(vararg user: RoomUser)

    @Query("DELETE FROM ROOMUSER WHERE COLUMN_NAME = :nama")
    fun deleteAll(nama : String)

}