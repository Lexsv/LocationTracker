package ua.com.location.models.savadata

import ua.com.location.data.LocatoinTrak
import ua.com.location.models.roommodel.RoomData
import ua.com.location.models.roommodel.RoomWorkInterfas

class SaveData: SaveDataInrerfas {
    var roomWorkInterfasv: RoomWorkInterfas
    init {
        roomWorkInterfasv = RoomData()
    }
    override fun saveToLostLocationTrak(locatoinTrak: LocatoinTrak) {
//        GlobalScope.launch {
//            val dataBaseObjact = roomWorkInterfasv.getById( StoreViewModel.idUser)
//            val  list = dataBaseObjact.listLocatoinTrak
//            list!!.plus(locatoinTrak)
//            roomWorkInterfasv.update(dataBaseObjact)
//        }

    }
}