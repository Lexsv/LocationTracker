package ua.com.location.data.room

import androidx.room.TypeConverter
import com.google.firebase.auth.FirebaseUser
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ua.com.location.data.LocatoinTrak


class Converters {
    @TypeConverter
    fun convertLocationTracToString(locatoinTrak: List<LocatoinTrak>) = Gson().toJson(locatoinTrak)

    @TypeConverter
    fun convertStringToLocationTrac(value: String): List<LocatoinTrak> =
        Gson().fromJson(value, object : TypeToken<List<LocatoinTrak>>() {}.type)

    @TypeConverter
    fun convertFirebaseUserToString(fierbaseUser: FirebaseUser) = Gson().toJson(fierbaseUser)

    @TypeConverter
    fun convertStringToFirebaseUser(value: String): FirebaseUser =
        Gson().fromJson(value, object : TypeToken<FirebaseUser>() {}.type)


}
