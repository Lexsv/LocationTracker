package ua.com.location.presentation.map

import ua.com.location.repository.data.LocalStoreVW
import javax.inject.Inject

class MapPrasentation @Inject constructor(var mapView: MapView): MapPresentationInterfas{


    override fun onSaveMyLocation(pairLocation: Pair<Double,Double>) {
        LocalStoreVW.getLastLocation().postValue(pairLocation)
    }


}