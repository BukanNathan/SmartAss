package com.example.converterapp2

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class RoomUser (
    @PrimaryKey var _id : Int,
    @ColumnInfo(name = "COLUMN_NAME") var name : String = "",
    @ColumnInfo(name = "COLUMN_EMAIL") var email : String = ""
)