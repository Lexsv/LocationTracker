package ua.com.location.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ua.com.location.data.repository.PostRepository
import ua.com.location.data.room.AppDatabase
import ua.com.location.data.room.DataBaseDAO
import ua.com.location.data.room.DataBaseObjact
import javax.inject.Inject

class PostViewModel @Inject constructor(application: Application): AndroidViewModel(application),PostVMInrefas {

    private val repository: PostRepository
    var allUser: LiveData<List<DataBaseObjact>>
     var appDatabase: AppDatabase

    init {
        appDatabase =  AppDatabase.getAppDatabase(application)
        val dataBaseDAO = appDatabase.dataBaseDou()

        repository = PostRepository(dataBaseDAO)
        allUser = repository.allUser
    }

    fun clos(){
        GlobalScope.launch { appDatabase.close() }
        }

    fun insert(dataBaseObjact: DataBaseObjact) = GlobalScope.launch {
        repository.insert(dataBaseObjact)
    }

    fun upDate(dataBaseObjact: DataBaseObjact){
        GlobalScope.launch {repository.upDate(dataBaseObjact)}
    }
    fun delete(dataBaseObjact: DataBaseObjact){
        GlobalScope.launch {repository.delete(dataBaseObjact)}
    }
    fun deleteAll(){
        GlobalScope.launch {repository.deleteAll()}
    }

}