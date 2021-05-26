package com.example.converterapp2

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(RoomUser::class),version = 1)
abstract class RoomMyDBRoomHelper : RoomDatabase() {
    abstract fun RoomUserDAO() : RoomUserDAO
}