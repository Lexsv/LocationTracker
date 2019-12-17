package ua.com.location.repository.room.userinfo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userinfo")
data class UserInfo(
    @PrimaryKey()
    var id: String,
    var name: String = "name empty",
    var lastId: Int = 0
)