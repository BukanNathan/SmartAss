package com.example.converterapp2

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class File(var Type: String = "Not Selected", var Extension: String = ".") : Parcelable {
}