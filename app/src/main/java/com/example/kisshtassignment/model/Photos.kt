package com.example.kisshtassignment.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Photos(
    val id: String,
    val created_at: String,
    val color: String? = "#000000",
    val description: String?,
    val urls: PhotoUrls
) : Parcelable
