package com.example.converterapp2

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FileData(var Type: String, var Extension: String = ".") : Parcelable {
}