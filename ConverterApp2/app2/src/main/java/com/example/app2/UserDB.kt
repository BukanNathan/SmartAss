package com.example.app2

import android.net.Uri
import android.provider.BaseColumns

object SqLiteuserDB {
    class userTable : BaseColumns {
        companion object{
            val TABLE_USER = "tbl_User"
            val COLUMN_ID = "User_Id"
            val COLUMN_NAME = "User_Name"
            val COLUMN_EMAIL = "User_Email"
        }
    }
}
class myContentProviderURI {
    companion object {
        val AUTHORITY = "com.example.converterapp2.provider.myContentProvider"
        val USER_TABLE = SqLiteuserDB.userTable.TABLE_USER
        val CONTENT_URI = Uri.parse("content://$AUTHORITY/$USER_TABLE")
    }
}