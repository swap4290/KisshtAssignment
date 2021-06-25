package com.example.kisshtassignment.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Position (
    val x: Float?,
    val y: Float?

) : Parcelable