package ua.com.location.repository.room.contant

import androidx.room.*

@Dao
interface ContentDao {
    @Query("SELECT * FROM content")
    fun getAll(): List<Content>

    @Query("SELECT * FROM content WHERE id = :id")
    fun getById(id: Int): Content

    @Query("DELETE FROM content")
    fun deleteAll()

    @Insert
    fun insert(content: Content)

    @Update
    fun update(content: Content)

    @Delete
    fun delete(content: Content)
}