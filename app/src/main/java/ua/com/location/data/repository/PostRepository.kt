package ua.com.location.data.repository

import androidx.lifecycle.LiveData
import ua.com.location.data.room.DataBaseDAO
import ua.com.location.data.room.DataBaseObjact

class PostRepository(private val dataBaseDAO: DataBaseDAO) {

    val allUser: LiveData<List<DataBaseObjact>> = dataBaseDAO.getAllUser()

    suspend fun insert(dataBaseObjact: DataBaseObjact){
        dataBaseDAO.insert(dataBaseObjact)
    }

    suspend fun upDate(dataBaseObjact: DataBaseObjact){
        dataBaseDAO.upDate(dataBaseObjact)
    }
    suspend fun delete(dataBaseObjact: DataBaseObjact){
        dataBaseDAO.delete(dataBaseObjact)
    }
    suspend fun deleteAll(){
        dataBaseDAO.deleteAll()
    }
}