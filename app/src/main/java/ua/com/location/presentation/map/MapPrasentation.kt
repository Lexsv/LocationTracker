package ua.com.location.presentation.map

import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ua.com.location.repository.data.LocalStoreVW
import ua.com.location.util.myLocation
import javax.inject.Inject

class MapPrasentation @Inject constructor(var mapView: MapView): MapPresentationInterfas{


    override fun onSaveMyLocation(pairLocation: Pair<Double,Double>) {
        LocalStoreVW.getLastLocation().postValue(pairLocation)
    }

    override fun onStart() {
        LocalStoreVW.nowFragment = "MAP"
        if (LocalStoreVW.creatWay){
                myLocation().addOnSuccessListener{
                   GlobalScope.launch(Dispatchers.Main) {
                       val from = LatLng(it.latitude,it.longitude)
                       val to = LatLng(LocalStoreVW.workingItom!!.latitude,LocalStoreVW.workingItom!!.longitude)
                       val linePath = mapView.getVM().getPoliline(from,to).await()
                       mapView.creatWay(linePath)
                   }
            }
            LocalStoreVW.creatWay = false
        }
    }


}