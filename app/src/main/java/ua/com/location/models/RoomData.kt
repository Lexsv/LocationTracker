package ua.com.location.models

import ua.com.location.data.ProvidContext
import ua.com.location.data.room.AppDatabase
import ua.com.location.data.room.DataBaseObjact
import javax.inject.Inject

class RoomData @Inject constructor(): RoomWorkInterfas {


    override fun getById(id: String): DataBaseObjact {
        val dataBase = AppDatabase.getAppDatabase(ProvidContext.getContext())
        val dataBaseInterfas = dataBase!!.dataBaseDou()
        val result = dataBaseInterfas.getById(id)
        AppDatabase.destroyInstance()
        return result
    }


    override fun insert(dataBaseObjact: DataBaseObjact) {
        val dataBase = AppDatabase.getAppDatabase(ProvidContext.getContext())
        val dataBaseInterfas = dataBase!!.dataBaseDou()
        dataBaseInterfas.insert(dataBaseObjact)
        AppDatabase.destroyInstance()
    }

    override fun update(dataBaseObjact: DataBaseObjact) {
        val dataBase = AppDatabase.getAppDatabase(ProvidContext.getContext())
        val dataBaseInterfas = dataBase!!.dataBaseDou()
        dataBaseInterfas.upDate(dataBaseObjact)
        AppDatabase.destroyInstance()
    }

    override fun delete(dataBaseObjact: DataBaseObjact) {
         val dataBase = AppDatabase.getAppDatabase(ProvidContext.getContext())
         val dataBaseInterfas = dataBase!!.dataBaseDou()
        dataBaseInterfas.delete(dataBaseObjact)
        AppDatabase.destroyInstance()
    }

    override fun deleteAllUser() {
        val dataBase = AppDatabase.getAppDatabase(ProvidContext.getContext())
        val dataBaseInterfas = dataBase!!.dataBaseDou()
        dataBaseInterfas.deleteAll()
        AppDatabase.destroyInstance()
    }

    override fun getAllUser(): List<DataBaseObjact> {
        val dataBase = AppDatabase.getAppDatabase(ProvidContext.getContext())
        val dataBaseInterfas = dataBase!!.dataBaseDou()
        val listUser = dataBaseInterfas.getAllUser()
        AppDatabase.destroyInstance()
        return listUser
    }
}