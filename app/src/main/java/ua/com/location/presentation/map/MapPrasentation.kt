package ua.com.location.presentation.map

import javax.inject.Inject

class MapPrasentation @Inject constructor(var mapView: MapView): MapPresentationInterfas{


    override fun onSaveMyLocation(pairLocation: Pair<Double,Double>) {

    }


}