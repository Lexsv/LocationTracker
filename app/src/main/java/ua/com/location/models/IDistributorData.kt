package ua.com.location.models

import ua.com.location.models.repository.room.contant.Content

interface IDistributorData {
    fun onStartApp()
    fun fillData()
    fun saveToRoom(any: Any)
    fun exit()
    fun saveToFire()
    fun getDataUserFromFir(id:String)
}