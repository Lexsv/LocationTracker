package ua.com.location.models.repository.firebase

import ua.com.location.models.repository.room.contant.Content

interface IDBOFireBase {

    suspend fun insert(list: List<Content>)

    suspend fun update(content: Content)


    fun getUserData(id: String)

    suspend fun deleteContent(id: String)


}