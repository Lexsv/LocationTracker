package ua.com.location.models.listandtrakModel

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PolylineOptions
import kotlinx.coroutines.Deferred
import ua.com.location.repository.room.content.Content

interface IListAndTrak {
    fun exit()
    fun removeItom(content : Content)
}