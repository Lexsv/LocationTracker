package ua.com.location.repository.room.contant

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "content")
data class Content(
    @PrimaryKey()
    @ColumnInfo(name = "id")
    var id: Int = 0,
    @ColumnInfo(name = "title")
    var title: String = "",
    @ColumnInfo(name = "descript")
    var descript: String = "",
    @ColumnInfo(name = "latitude")
    var latitude: Double = 0.0,
    @ColumnInfo(name = "longitude")
    var longitude: Double = 0.0
)