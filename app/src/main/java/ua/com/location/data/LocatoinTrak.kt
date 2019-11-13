package ua.com.location.data

import androidx.room.Entity
import androidx.room.PrimaryKey


data class LocatoinTrak (
    val title: String,
    val descript: String,
    val locatoin: Pair<Double,Double>
)