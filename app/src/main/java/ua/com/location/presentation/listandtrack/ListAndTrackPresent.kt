package ua.com.location.presentation.listandtrack

import ua.com.location.data.LocatoinTrak
import ua.com.location.data.interfas.DataModulInterfas

class ListAndTrackPresent constructor( var dataModulInterfas: DataModulInterfas,
                                       var listAndTrackView: ListAndTrackView
):
    ListAndTrackPresentInterfas {




    override fun getListLocation(): List<LocatoinTrak> =  dataModulInterfas.getListLocation()



}