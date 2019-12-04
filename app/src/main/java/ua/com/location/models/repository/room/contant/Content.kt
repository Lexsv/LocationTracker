package ua.com.location.models.repository.room.contant

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "content")
class Content(){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0
    @ColumnInfo(name = "title")
    var title: String = ""
    @ColumnInfo(name = "descript")
    var descript: String = ""
    @ColumnInfo(name = "latitude")
    var latitude: Double = 0.0
    @ColumnInfo(name = "longitude")
    var longitude: Double = 0.0


    override fun toString(): String {
        return "\n ID: $id \n TITLE: $title \n DESCRIPT: $descript \n LOCATION: $latitude : $longitude "
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Content

        if (id != other.id) return false
        if (title != other.title) return false
        if (descript != other.descript) return false
        if (latitude != other.latitude) return false
        if (longitude != other.longitude) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + title.hashCode()
        result = 31 * result + descript.hashCode()
        result = 31 * result + latitude.hashCode()
        result = 31 * result + longitude.hashCode()
        return result
    }
}