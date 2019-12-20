package ua.com.location.models.mapModel

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PolylineOptions
import kotlinx.coroutines.Deferred

interface IMap {
  fun getPoliline(from: LatLng, to: LatLng): Deferred<PolylineOptions>
}