package ua.com.location.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import ua.com.location.data.LocatoinTrak

@Entity(tableName = "user")
@TypeConverters(Converters::class)
class DataBaseObjact(
    @PrimaryKey  @ColumnInfo(name = "id") var id: String,
    @ColumnInfo(name = "listlocat") var listLocatoinTrak: List<LocatoinTrak>
)