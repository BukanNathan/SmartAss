package com.example.converterapp2

import SqLiteMyDB.SqLiteuserDB
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SqLiteMyDBHelper(context: Context) : SQLiteOpenHelper(
    context, DATABASE_NAME, null, DATABASE_VERSION)

{
    companion object{
        private val DATABASE_NAME = "mysqlite.db"
        private val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        var CREATE_USER_TABLE = "CREATE TABLE ${SqLiteuserDB.userTable.TABLE_USER}" +
                "(${SqLiteuserDB.userTable.COLUMN_ID} INTEGER PRIMARY KEY," +
                "${SqLiteuserDB.userTable.COLUMN_NAME} TEXT," +
                "${SqLiteuserDB.userTable.COLUMN_EMAIL} TEXT)"
        db?.execSQL(CREATE_USER_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS ${SqLiteuserDB.userTable.TABLE_USER}")
        onCreate(db)
    }

    fun adduser(user : SqLiteUser) : Long{
        var db = this.writableDatabase
        var contentValues = ContentValues().apply{
            put(SqLiteuserDB.userTable.COLUMN_NAME, user.nama)
            put(SqLiteuserDB.userTable.COLUMN_EMAIL, user.email)
        }
        var sucess = db.insert(SqLiteuserDB.userTable.TABLE_USER, null, contentValues)
        db.close()
        return sucess
    }

    fun viewAllName() : List<String>{
        val nameList = ArrayList<String>()
        val SELECT_NAME = "SELECT ${SqLiteuserDB.userTable.COLUMN_NAME} FROM " +
                "${SqLiteuserDB.userTable.TABLE_USER}"
        var db = this.readableDatabase
        var cursor : Cursor?=null
        try {
            cursor = db.rawQuery(SELECT_NAME, null)
        }catch (e : SQLException){
            return ArrayList()
        }
        var userName = ""
        if(cursor.moveToFirst()){
            do{
                userName = cursor.getString(cursor.getColumnIndex(SqLiteuserDB.userTable.COLUMN_NAME))
                nameList.add(userName)
            }while (cursor.moveToNext())
        }
        return nameList
    }

    fun deleteUser(nama : String){
        var db = this.writableDatabase
        var selection = "${SqLiteuserDB.userTable.COLUMN_NAME} = ?"
        var selectionArgs = arrayOf(nama)
        db.delete(SqLiteuserDB.userTable.TABLE_USER,null, null)
    }

    fun deleteAll(){
        var db = this.writableDatabase
        db.delete(SqLiteuserDB.userTable.TABLE_USER, null, null)
    }
}