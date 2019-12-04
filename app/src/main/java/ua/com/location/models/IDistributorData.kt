package ua.com.location.models

import com.google.firebase.auth.FirebaseAuth
import ua.com.location.models.repository.room.contant.Content

interface IDistributorData {
    fun onStartApp()
    fun fillData()
    fun saveToRoom(any: Any)
    fun exit()
    fun saveToFire()
    fun getDataUserFromFir(id:String)
    fun registr(mAuth : FirebaseAuth)
}