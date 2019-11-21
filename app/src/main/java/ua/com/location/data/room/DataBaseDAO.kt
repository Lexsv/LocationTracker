package ua.com.location.data.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DataBaseDAO{

    @Query("SELECT * FROM user")
    fun getAllUser(): LiveData<List<DataBaseObjact>>


    @Query("SELECT * FROM user WHERE id = :id")
    fun getById(id: String): DataBaseObjact

    @Insert
    fun insert( dataBaseObjact: DataBaseObjact)

    @Update
    fun upDate(dataBaseObjact: DataBaseObjact)

    @Delete
    fun delete(dataBaseObjact: DataBaseObjact)

    @Query("DELETE FROM user")
    fun deleteAll()


}