package ua.com.location.presentation.map

import com.google.android.gms.maps.model.PolylineOptions
import ua.com.location.models.mapModel.IMap

interface MapView {
    fun creatWay(polylineOptions: PolylineOptions)
    fun getVM(): IMap
}