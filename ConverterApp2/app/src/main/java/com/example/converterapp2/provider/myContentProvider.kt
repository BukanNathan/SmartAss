package com.example.converterapp2.provider

import SqLiteMyDB.SqLiteuserDB
import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteQueryBuilder
import android.net.Uri
import com.example.converterapp2.SqLiteMyDBHelper

class myContentProvider : ContentProvider() {
    private var dbHelper : SqLiteMyDBHelper?=null
    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        TODO("Not yet implemented")
    }

    override fun query(
        p0: Uri, //uri
        p1: Array<out String>?, //projection
        p2: String?, //selection
        p3: Array<out String>?, //selectionArguments
        p4: String? //sortOrder
    ): Cursor? {
        var queryBuilder = SQLiteQueryBuilder()
        queryBuilder.tables = SqLiteuserDB.userTable.TABLE_USER
        var cursor : Cursor = queryBuilder.query(dbHelper?.readableDatabase,
            p1, p2, p3, null, null, p4)
        cursor.setNotificationUri(context?.contentResolver, p0)
        return cursor
    }

    override fun onCreate(): Boolean {
        dbHelper = SqLiteMyDBHelper(context!!)
        return  true
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        TODO("Not yet implemented")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun getType(uri: Uri): String? {
        TODO("Not yet implemented")
    }
    companion object {
        val AUTHORITY = "com.example.converterapp2.provider.provider.myContentProvider"
        val USER_TABLE = SqLiteuserDB.userTable.TABLE_USER
        val CONTENT_URI = Uri.parse("content://$AUTHORITY/$USER_TABLE")
    }
}