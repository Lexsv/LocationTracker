package ua.com.location.data

import ua.com.location.data.interfas.DataModulInterfas
import ua.com.location.presentor.listandtrack.ListAndTrackPresentInterfas

class DataModel constructor(var listAndTrackPresentInterfas: ListAndTrackPresentInterfas): DataModulInterfas {

    override fun getListLocation(): List<LocatoinTrak> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object{

    }


}