package ua.com.location.models.repository.room.userinfo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userinfo")
class UserInfo(
    @PrimaryKey()
    var id: String,
    var name: String = "name empty"
) {
    override fun toString(): String {
        return "\n ID: $id \n NAME: $name"
    }

}