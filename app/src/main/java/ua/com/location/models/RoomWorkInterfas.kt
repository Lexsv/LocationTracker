package ua.com.location.models

import ua.com.location.data.room.DataBaseObjact

interface RoomWorkInterfas {

    fun insert(dataBaseObjact: DataBaseObjact)

    fun update(dataBaseObjact: DataBaseObjact)

    fun delete(dataBaseObjact: DataBaseObjact)

    fun deleteAllUser()

    fun getAllUser(): List<DataBaseObjact>

    fun getById(id: String): DataBaseObjact
}