package ua.com.location.data.room

import androidx.room.*

@Dao
interface DataBaseInterfas{

    @Query("SELECT * FROM user")
    fun getAllUser(): List<DataBaseObjact>


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