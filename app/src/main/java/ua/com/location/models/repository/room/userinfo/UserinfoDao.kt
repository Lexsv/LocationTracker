package ua.com.location.models.repository.room.userinfo

import androidx.room.*


@Dao
interface UserinfoDao {

    @Query("SELECT * FROM userinfo")
    fun getAll(): List<UserInfo>

    @Query("SELECT * FROM userinfo WHERE id = :id")
    fun getById(id: Long): UserInfo

    @Query("DELETE FROM userinfo")
    fun deleteAll()

    @Insert
    fun insert(userInfo: UserInfo)

    @Update
    fun update(userInfo: UserInfo)

    @Delete
    fun delete(userInfo: UserInfo)

}