package ua.com.location.models.roommodel

import ua.com.location.data.room.DataBaseObjact

interface RoomWorkInterfas {

    suspend fun insert(dataBaseObjact: DataBaseObjact)

    suspend fun update(dataBaseObjact: DataBaseObjact)

    suspend fun delete(dataBaseObjact: DataBaseObjact)

    suspend fun deleteAllUser()

    suspend fun getAllUser(): List<DataBaseObjact>

    suspend fun getById(id: String): DataBaseObjact
}